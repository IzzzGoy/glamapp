package ru.homecraft.glamapp.domain.impl.usecases

import arrow.core.*
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.fx.coroutines.parMap
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.koin.core.annotation.Factory
import ru.homecraft.glamapp.data.api.local.OrdersLocalRepository
import ru.homecraft.glamapp.data.api.models.*
import ru.homecraft.glamapp.data.api.remote.OrdersRemoteRepository
import ru.homecraft.glamapp.domain.api.usecases.GetOrdersUseCase
import ru.homecraft.glamapp.utils.AppError
import ru.homecraft.glamapp.utils.Logger
import ru.homecraft.glamapp.utils.MappingError
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
//@ViewModelScope
@Factory(binds = [GetOrdersUseCase::class])
class GetOrdersUseCaseImpl(
    private val ordersLocalRepository: OrdersLocalRepository,
    private val ordersRemoteRepository: OrdersRemoteRepository,
) : GetOrdersUseCase {

    override suspend fun invoke(): Either<AppError, Int> =
        Logger.suspendTrace("GetOrdersUseCaseImpl") {
            either {
                val remoteData = ordersRemoteRepository.load(0, 15).bind()

                map(remoteData).flatMap {
                    ordersLocalRepository.add(it)
                }.onRight {
                    Logger.log("Successful data save!")
                }.onLeft {
                    Logger.log("Saving data error: $it")
                }.bind()
                remoteData.size
            }
        }

    private suspend fun map(ordersPageModel: OrdersPageModel): Either<AppError, List<OrderEntity>> = either {
        OrdersPageModel.pageData.get(ordersPageModel).parMap { model ->
            val id = either {
                ensure(model.id >= 0) {
                    MappingError("Id in model: $model must be rather then 0!")
                }
                model.id.toLong()
            }
            val createdAt = catch(
                { model.createdAt.toInstant(TimeZone.UTC).toEpochMilliseconds().right() },
                { MappingError("Can`t parse date to millis: $model").left() }
            )
            val description = OrderData.description.get(model).toOption()
            OrderEntity(
                id = id.bind(),
                status = model.status.ordinal.toLong(),
                createdAt = createdAt.bind(),
                description = description.getOrNull(),
            )
        }
    }
}
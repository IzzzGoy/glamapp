package ru.homecraft.glamapp.domain.impl.usecases

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.fx.coroutines.parMap
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.koin.core.annotation.Factory
import ru.homecraft.glamapp.data.api.local.OrdersLocalRepository
import ru.homecraft.glamapp.data.api.models.OrderEntity
import ru.homecraft.glamapp.data.api.remote.OrdersRemoteRepository
import ru.homecraft.glamapp.domain.api.usecases.GetOrdersUseCase
import ru.homecraft.glamapp.utils.AppError
import ru.homecraft.glamapp.utils.DataError
import ru.homecraft.glamapp.utils.Logger
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
                catch(
                    block = {
                        ordersLocalRepository.add(remoteData.pageData.parMap {
                            OrderEntity(
                                id = it.id.toLong(),
                                status = it.status.ordinal.toLong(),
                                createdAt = it.createdAt.toInstant(TimeZone.UTC).toEpochMilliseconds(),
                                description = it.description
                            )
                        })
                    }
                ) {
                    raise(DataError.LocalDataError.DatabaseWrightError(it.message.orEmpty()))
                }
                remoteData.size
            }
        }
}
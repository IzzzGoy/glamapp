package ru.homecraft.glamapp.domain.impl.usecases

import arrow.core.Either
import arrow.core.raise.either
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.annotation.Factory
import ru.homecraft.glamapp.domain.api.model.Order
import ru.homecraft.glamapp.domain.api.model.OrderList
import ru.homecraft.glamapp.domain.api.model.OrderStatus
import ru.homecraft.glamapp.domain.api.usecases.GetOrdersUseCase
import ru.homecraft.glamapp.utils.AppError
import ru.homecraft.glamapp.utils.Logger
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
//@ViewModelScope
@Factory(binds = [GetOrdersUseCase::class])
class GetOrdersUseCaseImpl: GetOrdersUseCase {

    override suspend fun invoke(): Either<AppError, OrderList> = either {
        Logger.trace("GetOrdersUseCaseImpl") {
            OrderList(
                orders = (0..15).map {
                    Order(
                        id = it,
                        status = OrderStatus.Pending,
                        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
                    )
                }
            )
        }
    }
}
package ru.homecraft.glamapp.domain.impl.providers

import arrow.fx.coroutines.parMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.annotation.Factory
import ru.homecraft.glamapp.data.api.local.OrdersLocalRepository
import ru.homecraft.glamapp.domain.api.model.Order
import ru.homecraft.glamapp.domain.api.model.OrderList
import ru.homecraft.glamapp.domain.api.model.OrderStatus
import ru.homecraft.glamapp.domain.api.poviders.OrdersListProvider
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Factory(binds = [OrdersListProvider::class])
@OptIn(ExperimentalTime::class)
class OrdersListProviderImpl(
    ordersLocalRepository: OrdersLocalRepository,
): OrdersListProvider {

    override val orders: Flow<OrderList> = ordersLocalRepository.orders.map { orders ->
        OrderList(
            orders.parMap {
                Order(
                    id = it.id.toInt(),
                    status = when(it.status) {
                        0L -> {
                            OrderStatus.Success
                        }

                        1L -> {
                            OrderStatus.Rejected(it.description.orEmpty())
                        }

                        2L -> {
                            OrderStatus.Pending
                        }

                        else -> {
                            throw IllegalStateException("Status must be in 1..3 but ${it.status} provided")
                        }
                    },
                    createdAt = Instant
                        .fromEpochMilliseconds(it.createdAt)
                        .toLocalDateTime(TimeZone.UTC),
                )
            }
        )
    }
}
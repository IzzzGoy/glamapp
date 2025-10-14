package ru.homecraft.glamapp.domain.api.model

import arrow.optics.optics
import kotlinx.datetime.LocalDateTime

@optics
data class Order(
    val id: Int,
    val status: OrderStatus,
    val createdAt: LocalDateTime,
) { companion object }

sealed interface OrderStatus {
    data object Success: OrderStatus
    data class Rejected(val cause: String): OrderStatus
    data object Pending: OrderStatus
}

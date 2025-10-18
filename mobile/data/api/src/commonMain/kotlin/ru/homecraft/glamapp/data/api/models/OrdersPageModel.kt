package ru.homecraft.glamapp.data.api.models

import arrow.optics.optics
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
@optics
data class OrdersPageModel(val size: Int, val pageData: List<OrderData>) {
    companion object
}


@optics
@Serializable
data class OrderData(
    val id: Int,
    val status: OrderStatus,
    val description: String? = null,
    val createdAt: LocalDateTime,
) {
    companion object
}


enum class OrderStatus {
    Open, Closed, Pending
}
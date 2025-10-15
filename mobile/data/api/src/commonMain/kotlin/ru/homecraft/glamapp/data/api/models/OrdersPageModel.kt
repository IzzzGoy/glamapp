package ru.homecraft.glamapp.data.api.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class OrdersPageModel(val size: Int, val pageData: List<OrderData>)
@Serializable
data class OrderData(
    val id: Int,
    val status: OrderStatus,
    val description: String? = null,
    val createdAt: LocalDateTime,
)


enum class OrderStatus {
    Open, Closed, Pending
}
package ru.homecraft.glamapp.domain.api.model

import arrow.optics.optics

@optics
data class OrderList(val orders: List<Order>) {
    companion object
}

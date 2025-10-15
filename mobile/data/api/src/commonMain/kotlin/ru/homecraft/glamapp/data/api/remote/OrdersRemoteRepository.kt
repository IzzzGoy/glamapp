package ru.homecraft.glamapp.data.api.remote

import arrow.core.Either
import ru.homecraft.glamapp.data.api.models.OrdersPageModel
import ru.homecraft.glamapp.utils.AppError

interface OrdersRemoteRepository {
    suspend fun load(page: Int, size: Int): Either<AppError, OrdersPageModel>
}
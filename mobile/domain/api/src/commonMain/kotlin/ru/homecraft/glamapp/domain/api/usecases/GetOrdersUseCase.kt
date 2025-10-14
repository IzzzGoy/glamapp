package ru.homecraft.glamapp.domain.api.usecases

import arrow.core.Either
import ru.homecraft.glamapp.domain.api.model.OrderList
import ru.homecraft.glamapp.utils.AppError

interface GetOrdersUseCase {
    suspend operator fun invoke() : Either<AppError, OrderList>
}
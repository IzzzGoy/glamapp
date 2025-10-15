package ru.homecraft.glamapp.presentation.impl

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import ru.homecraft.glamapp.domain.api.poviders.OrdersListProvider
import ru.homecraft.glamapp.domain.api.usecases.GetOrdersUseCase
import ru.homecraft.glamapp.presentation.api.HomeViewModel
import ru.homecraft.glamapp.utils.Logger

@KoinViewModel(binds = [HomeViewModel::class])
class HomeViewModelImpl(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val ordersListProvider: OrdersListProvider,
): HomeViewModel() {

    init {
        viewModelScope.launch {
            getOrdersUseCase()
                .onRight { list ->
                    Logger.log("Success load orders $list!")
                }
                .onLeft {
                    Logger.log("Success load orders with error: $it!")
                }
        }
        viewModelScope.launch {
            ordersListProvider.orders.collect {
                update(
                    HomeViewModelState(
                        it.orders.map { (id, status, createdAt) -> id }
                    )
                )
            }
        }
    }

    override fun dispatch(action: HomeViewModelAction) {
        when(action) {
            HomeViewModelAction.OnCreateOrderClick -> {
                effect(HomeViewModelEffect.GoToCreateOrder)
            }
        }
    }
}
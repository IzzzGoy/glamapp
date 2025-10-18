package ru.homecraft.glamapp.presentation.impl

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import ru.homecraft.glamapp.domain.api.model.OrderList
import ru.homecraft.glamapp.domain.api.model.orders
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
        state.onStart {
            getOrdersUseCase()
                .onRight { list ->
                    Logger.log("Success load orders $list!")
                }
                .onLeft {
                    Logger.log("Success load orders with error: $it!")
                }
        }.launchIn(viewModelScope)
        viewModelScope.launch {
            ordersListProvider.orders.collect {
                update(
                    HomeViewModelState(
                        OrderList.orders.get(it).map { (id, status, createdAt) -> id }
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
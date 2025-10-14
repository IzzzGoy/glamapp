package ru.homecraft.glamapp.presentation.impl

import androidx.lifecycle.viewModelScope
import arrow.optics.copy
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import ru.homecraft.glamapp.domain.api.usecases.GetOrdersUseCase
import ru.homecraft.glamapp.presentation.api.HomeViewModel
import ru.homecraft.glamapp.presentation.api.items

@KoinViewModel(binds = [HomeViewModel::class])
class HomeViewModelImpl(
    private val getOrdersUseCase: GetOrdersUseCase,
): HomeViewModel() {

    init {
        viewModelScope.launch {
            getOrdersUseCase()
                .onRight { list ->
                    update(
                        state.value.copy {
                            HomeViewModelState.items set list.orders.map { (id, _, _) ->
                                id
                            }
                        }
                    )
                }
                .onLeft {
                    // TODO
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
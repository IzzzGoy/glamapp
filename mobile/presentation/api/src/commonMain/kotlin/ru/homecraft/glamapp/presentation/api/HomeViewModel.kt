package ru.homecraft.glamapp.presentation.api

import arrow.optics.optics

abstract class HomeViewModel: BaseViewModel<
        HomeViewModel.HomeViewModelState,
        HomeViewModel.HomeViewModelAction,
        HomeViewModel.HomeViewModelEffect
        >(HomeViewModelState()) {

    @optics
    data class HomeViewModelState(
        val items: List<Int> = (0..10).toList()
    ) { companion object }
    sealed interface HomeViewModelEffect {
        data object GoToCreateOrder: HomeViewModelEffect
    }

    sealed interface HomeViewModelAction {
        data object OnCreateOrderClick : HomeViewModelAction
    }
}
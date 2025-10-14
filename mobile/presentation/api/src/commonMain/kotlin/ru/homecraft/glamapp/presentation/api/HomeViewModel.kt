package ru.homecraft.glamapp.presentation.api

abstract class HomeViewModel: BaseViewModel<
        HomeViewModel.HomeViewModelState,
        HomeViewModel.HomeViewModelAction,
        HomeViewModel.HomeViewModelEffect
        >(HomeViewModelState()) {
    data class HomeViewModelState(
        val items: List<Int> = (0..10).toList()
    )
    sealed interface HomeViewModelEffect {
        data object GoToCreateOrder: HomeViewModelEffect
    }

    sealed interface HomeViewModelAction {
        data object OnCreateOrderClick : HomeViewModelAction
    }
}
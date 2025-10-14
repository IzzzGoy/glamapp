package ru.homecraft.glamapp.presentation.impl

import org.koin.android.annotation.KoinViewModel
import ru.homecraft.glamapp.presentation.api.HomeViewModel

@KoinViewModel(binds = [HomeViewModel::class])
class HomeViewModelImpl: HomeViewModel() {

    override fun dispatch(action: HomeViewModelAction) {
        when(action) {
            HomeViewModelAction.OnCreateOrderClick -> {
                effect(HomeViewModelEffect.GoToCreateOrder)
            }
        }
    }
}
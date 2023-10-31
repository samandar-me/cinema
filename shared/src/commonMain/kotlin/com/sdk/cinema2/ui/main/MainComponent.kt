package com.sdk.cinema2.ui.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class MainComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: (Output) -> Unit
): ComponentContext by componentContext {
    private val mainStore = instanceKeeper.getStore {
        MainStoreFactory(
            storeFactory = storeFactory
        ).create()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<MainStore.State> = mainStore.stateFlow

    fun onEvent(event: MainStore.Intent) {
        mainStore.accept(event)
    }

    fun onOutput(output: Output) {
        output(output)
    }

    sealed interface Output {
        data class OnMovieClicked(val movieId: Int): Output
    }
}
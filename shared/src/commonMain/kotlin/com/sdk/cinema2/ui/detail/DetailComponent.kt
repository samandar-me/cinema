package com.sdk.cinema2.ui.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class DetailComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    movieId: Int,
    private val output: (Output) -> Unit
): ComponentContext by componentContext {
    private val detailStore = instanceKeeper.getStore {
        DetailStoreFactory(
            storeFactory = storeFactory,
            movieId = movieId
        ).create()
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<DetailStore.State> = detailStore.stateFlow

    fun onEvent(event: DetailStore.Intent) {
        detailStore.accept(event)
    }
    fun onOutput(output: Output) {
        output(output)
    }
    sealed class Output {
        data object NavigateBack: Output()
    }
}
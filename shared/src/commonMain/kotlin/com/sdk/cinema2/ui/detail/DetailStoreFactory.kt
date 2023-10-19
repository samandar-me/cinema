package com.sdk.cinema2.ui.detail

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.sdk.cinema2.appDispatchers

internal class DetailStoreFactory(
    private val storeFactory: StoreFactory,
    private val movieId: Int
) {
    fun create(): DetailStore =
        object : DetailStore, Store<DetailStore.Intent, DetailStore.State, Nothing> by storeFactory.create(
            name = "DetailStore",
            initialState = DetailStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        )  {}

    private sealed class Msg {
        object DetailLoading: Msg()
        data class DetailLoaded(val future: String): Msg()
        data class DetailFailed(val error: String?): Msg()
    }
    private inner class ExecutorImpl: CoroutineExecutor<DetailStore.Intent, Unit, DetailStore.State, Msg, Nothing>(
        appDispatchers.main
    ) {
        override fun executeIntent(intent: DetailStore.Intent, getState: () -> DetailStore.State) {
            when(intent) {
                is DetailStore.Intent.Test -> {

                }
            }
        }
    }
    private object ReducerImpl: Reducer<DetailStore.State, Msg> {
        override fun DetailStore.State.reduce(msg: Msg): DetailStore.State {
            return when(msg) {
                is Msg.DetailLoading -> DetailStore.State(isLoading = true)
                is Msg.DetailLoaded -> DetailStore.State()
                is Msg.DetailFailed -> DetailStore.State(isLoading = false, error = msg.error)
            }
        }
    }
}
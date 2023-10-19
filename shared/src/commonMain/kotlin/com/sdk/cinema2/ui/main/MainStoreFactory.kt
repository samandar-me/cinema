package com.sdk.cinema2.ui.main

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.sdk.cinema2.appDispatchers

internal class MainStoreFactory(
    private val storeFactory: StoreFactory
) {
    fun create(): MainStore =
        object : MainStore,
            Store<MainStore.Intent, MainStore.State, Nothing> by storeFactory.create(
                name = "MainStore",
                initialState = MainStore.State(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed interface Search {
        data class OnSearched(val query: String) : Search
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<MainStore.Intent, Unit, MainStore.State, Search, Nothing>(
            appDispatchers.io
        ) {
        override fun executeIntent(intent: MainStore.Intent, getState: () -> MainStore.State) {
            when (intent) {
                is MainStore.Intent.OnMovieSearch -> {
                    dispatch(Search.OnSearched(intent.query))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<MainStore.State, Search> {
        override fun MainStore.State.reduce(msg: Search): MainStore.State =
            when (msg) {
                is Search.OnSearched -> copy(query = msg.query)
            }
    }
}
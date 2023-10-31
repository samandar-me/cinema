package com.sdk.cinema2.ui.main

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.sdk.cinema2.appDispatchers
import com.sdk.cinema2.data.di.AppModule
import com.sdk.cinema2.data.model.Movie
import com.sdk.cinema2.data.repository.IMovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

internal class MainStoreFactory(
    private val storeFactory: StoreFactory
): DIAware {

    override val di: DI = AppModule.di
    private val repository by di.instance<IMovieRepository>()

    fun create(): MainStore =
        object : MainStore,
            Store<MainStore.Intent, MainStore.State, Nothing> by storeFactory.create(
                name = "MainStore",
                initialState = MainStore.State(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed interface Message {
        data object Loading: Message
        data class MoviesLoaded(val movieList: List<Movie>): Message
        data class MoviesLoaded2(val movieList: List<Movie>): Message
        data class Failed(val error: String?): Message
        data class OnSearched(val query: String): Message
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<MainStore.Intent, Unit, MainStore.State, Message, Nothing>(
            appDispatchers.main
        ) {
        override fun executeAction(action: Unit, getState: () -> MainStore.State) {
            loadMovies((1..10).random())
        }
        override fun executeIntent(intent: MainStore.Intent, getState: () -> MainStore.State) {
            when (intent) {
                is MainStore.Intent.OnMovieSearch -> {
                    dispatch(Message.OnSearched(intent.query))
                }
            }
        }
        private fun loadMovies(page: Int) {
            scope.launch {
                repository.getMovies(page)
                    .onStart {
                        dispatch(Message.Loading)
                    }
                    .catch { t ->
                        dispatch(Message.Failed(t.message))
                    }
                    .collectLatest { list ->
                        dispatch(Message.MoviesLoaded(list))
                    }
                repository.getMovies((1..10).random())
                    .catch { t ->
                        dispatch(Message.Failed(t.message))
                    }
                    .collectLatest {  list ->
                        dispatch(Message.MoviesLoaded2(list))
                    }
            }
        }
    }

    private object ReducerImpl : Reducer<MainStore.State, Message> {
        override fun MainStore.State.reduce(msg: Message): MainStore.State =
            when (msg) {
                is Message.Loading -> copy(isLoading = true)
                is Message.Failed -> copy(isLoading = false, error = msg.error)
                is Message.OnSearched -> copy(isLoading = true)
                is Message.MoviesLoaded -> copy(isLoading = false, success = msg.movieList)
                is Message.MoviesLoaded2 -> copy(success2 = msg.movieList)
            }
    }
}
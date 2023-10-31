package com.sdk.cinema2.ui.detail

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
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

internal class DetailStoreFactory(
    private val storeFactory: StoreFactory,
    private val movieId: Int
) : DIAware{

    override val di: DI = AppModule.di
    private val repository by di.instance<IMovieRepository>()

    fun create(): DetailStore =
        object : DetailStore, Store<DetailStore.Intent, DetailStore.State, Nothing> by storeFactory.create(
            name = "DetailStore",
            initialState = DetailStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        )  {}

    private sealed class Msg {
        data object DetailLoading: Msg()
        data class DetailLoaded(val movie: Movie?): Msg()
        data class DetailFailed(val error: String?): Msg()
    }
    private inner class ExecutorImpl: CoroutineExecutor<DetailStore.Intent, Unit, DetailStore.State, Msg, Nothing>(
        appDispatchers.main
    ) {
        override fun executeAction(action: Unit, getState: () -> DetailStore.State) {
            scope.launch {
                repository.getMovie(movieId)
                    .onStart {
                        dispatch(Msg.DetailLoading)
                    }
                    .catch { t ->
                        dispatch(Msg.DetailFailed(t.message))
                    }
                    .collectLatest { movie ->
                        dispatch(Msg.DetailLoaded(movie))
                    }
            }
        }
//        override fun executeIntent(intent: DetailStore.Intent, getState: () -> DetailStore.State) {
//            when(intent) {
//                is DetailStore.Intent.Test -> {
//
//                }
//            }
//        }
    }
    private object ReducerImpl: Reducer<DetailStore.State, Msg> {
        override fun DetailStore.State.reduce(msg: Msg): DetailStore.State {
            return when(msg) {
                is Msg.DetailLoading -> DetailStore.State(isLoading = true)
                is Msg.DetailLoaded -> DetailStore.State(isLoading = false, success = msg.movie)
                is Msg.DetailFailed -> DetailStore.State(isLoading = false, error = msg.error)
            }
        }
    }
}
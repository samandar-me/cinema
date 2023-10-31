package com.sdk.cinema2.ui.main

import com.arkivanov.mvikotlin.core.store.Store
import com.sdk.cinema2.data.model.Movie

interface MainStore: Store<MainStore.Intent, MainStore.State, Nothing>{

    sealed interface Intent {
        data class OnMovieSearch(val query: String): Intent
    }
    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val success: List<Movie> = emptyList(),
        val success2: List<Movie> = emptyList(),
        val searchQuery: String = ""
    )
}
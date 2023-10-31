package com.sdk.cinema2.ui.detail

import com.arkivanov.mvikotlin.core.store.Store
import com.sdk.cinema2.data.model.Movie

interface DetailStore : Store<DetailStore.Intent, DetailStore.State, Nothing> {
    sealed interface Intent {
        data object Test: Intent
    }
    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val success: Movie? = null
    )
}
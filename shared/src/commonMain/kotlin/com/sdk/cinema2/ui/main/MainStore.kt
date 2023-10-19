package com.sdk.cinema2.ui.main

import com.arkivanov.mvikotlin.core.store.Store

interface MainStore: Store<MainStore.Intent, MainStore.State, Nothing>{

    sealed interface Intent{
        data class OnMovieSearch(val query: String): Intent
    }
    data class State(
        val query: String = ""
    )
}
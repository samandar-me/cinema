package com.sdk.cinema2.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.sdk.cinema2.ui.detail.DetailComponent
import com.sdk.cinema2.ui.main.MainComponent

class RootComponent internal constructor(
    componentContext: ComponentContext,
    private val main: (ComponentContext, (MainComponent.Output) -> Unit) -> MainComponent,
    private val detail: (ComponentContext, movieId: Int, (DetailComponent.Output) -> Unit) -> DetailComponent
): ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory
    ): this (
        componentContext = componentContext,
        main = { childContext, output ->
            MainComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output
            )
        },
        detail = { childContext, movieId, output ->
            DetailComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                movieId = movieId,
                output = output
            )
        }
    )

    private val navigation = StackNavigation<Configuration>()

    private val stack = childStack(
        source = navigation,
        initialConfiguration = Configuration.Main,
        handleBackButton = true,
        childFactory = ::createChild
    )

    val childStack: Value<ChildStack<*, Child>> = stack

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): Child =
        when(configuration) {
            is Configuration.Main -> Child.Main(main(componentContext, ::onMainOutput))
            is Configuration.Detail -> Child.Detail(detail(componentContext, configuration.movieId, ::onDetailOutput))
        }

    private fun onMainOutput(output: MainComponent.Output) = when(output) {
        is MainComponent.Output.OnMovieClicked -> navigation.push(Configuration.Detail(output.movieId))
    }

    private fun onDetailOutput(output: DetailComponent.Output) = when(output) {
        is DetailComponent.Output.NavigateBack -> navigation.pop()
    }

    private sealed interface Configuration: Parcelable {
        @Parcelize
        data object Main : Configuration

        @Parcelize
        class Detail(val movieId: Int): Configuration
    }

    sealed class Child {
        data class Main(val component: MainComponent): Child()
        data class Detail(val component: DetailComponent): Child()
    }
}
package com.sdk.cinema2.data.di

import com.sdk.cinema2.data.repository.IMovieRepository
import com.sdk.cinema2.data.repository.MovieRepository
import com.sdk.cinema2.network.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

object AppModule {
    val appModule = DI.Module("appModule") {
        bind<IMovieRepository>() with singleton { MovieRepository(instance()) }
        bindSingleton {
            NetworkService(instance())
        }
        bindSingleton {
            HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                        encodeDefaults = true
                        coerceInputValues = true
                    })
                }
            }
        }
    }

    val di = DI {
        import(appModule)
    }
}
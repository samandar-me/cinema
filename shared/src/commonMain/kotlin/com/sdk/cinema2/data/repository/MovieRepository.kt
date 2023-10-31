package com.sdk.cinema2.data.repository

import com.sdk.cinema2.data.model.Movie
import com.sdk.cinema2.network.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.kodein.di.DI
import org.kodein.di.instance


internal interface IMovieRepository {
    suspend fun getMovies(page: Int = 0): Flow<List<Movie>>
    suspend fun getMovie(id: Int): Flow<Movie?>
}

internal class MovieRepository(
    private val service: NetworkService
): IMovieRepository {
    override suspend fun getMovies(page: Int): Flow<List<Movie>> = flow {
        val response = service.getMovies(page)
        emit(response.results)
    }

    override suspend fun getMovie(id: Int): Flow<Movie?> = flow {
        emit(service.getMovieById(id))
    }
}
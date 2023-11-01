package com.sdk.cinema2.network

import com.sdk.cinema2.data.model.Movie
import com.sdk.cinema2.data.model.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom

const val API_KEY = "8c2bbadabd465e81466586449631be0f" // 8c2bbadabd465e81466586449631be0f 10b5f11775d915de02e73630cccd9f1d
const val BASE_URL = "https://api.themoviedb.org/"

internal class NetworkService(
     private val client: HttpClient
 ) {
    suspend fun getMovies(page: Int = 1): MovieResponse = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getMovieById(id: Int): Movie {
        return client.get { pathUrl("movie/$id") }.body()
    }
}
fun HttpRequestBuilder.pathUrl(path: String) {
    url {
        takeFrom(BASE_URL)
        path("3",path)
        parameter("api_key", API_KEY)
    }
}
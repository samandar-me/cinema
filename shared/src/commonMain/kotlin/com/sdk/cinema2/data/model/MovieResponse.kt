package com.sdk.cinema2.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieResponse(
    @Serializable
    val results: List<Movie>
)
@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("vote_count")
    val count: Int,
    @SerialName("vote_average")
    val vote: Double,
    @SerialName("backdrop_path")
    val backImage: String,
    @SerialName("poster_path")
    val posterImage: String,
    @SerialName("production_companies")
    val companies: List<Company> = emptyList(),
    @SerialName("release_date")
    val releaseDate: String,
    val popularity: Double,
    @SerialName("genres")
    val genres: List<Genre> = emptyList()
)

@Serializable
data class Company(
    val id: Int,
    val name: String,
    @SerialName("logo_path")
    val logo: String?
)
@Serializable
data class Genre(
    val id: Int,
    val name: String
)
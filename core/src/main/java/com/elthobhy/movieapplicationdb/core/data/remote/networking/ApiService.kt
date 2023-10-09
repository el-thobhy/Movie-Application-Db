package com.elthobhy.movieapplicationdb.core.data.remote.networking

import com.elthobhy.movieapplicationdb.core.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ): MovieResponse

}
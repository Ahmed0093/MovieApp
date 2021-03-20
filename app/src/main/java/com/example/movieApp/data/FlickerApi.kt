package com.example.movieApp.data

import com.example.myapplication.data.FlickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerApi {
    @GET("?method=flickr.photos.search")
    suspend fun getFlickerImgResponse(
        @Query("text") movieTitle: String,
        @Query("page") page: Int = 1,
        @Query("api_key") api_key: String? = "a04930c38aed578c9fde8ecca13036c5",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("per_page") per_page: Int = 10
    ): Response<FlickerResponse>
}
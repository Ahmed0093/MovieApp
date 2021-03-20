package com.example.movieApp.repository

import com.example.movieApp.data.FlickerApi
import com.example.movieApp.network.NetworkRoute.makeApiCall
import com.example.movieApp.network.ResultType
import com.example.myapplication.data.FlickerResponse
import com.example.myapplication.helper.networkModuleProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickerRepo {

    private val flickerApi: FlickerApi by lazy {
        networkModuleProvider.providesRetrofit().create(
            FlickerApi::class.java
        )
    }

    suspend fun getFlickerImgResponse(
        movieTitle: String,
    ): ResultType<FlickerResponse> {
        return withContext(Dispatchers.IO) {
            makeApiCall {
                flickerApi.getFlickerImgResponse(
                    movieTitle = movieTitle
                )
            }
        }
    }
}
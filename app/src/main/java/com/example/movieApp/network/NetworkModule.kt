package com.example.movieApp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {

    private val API_URL = "https://api.flickr.com/services/rest/"

    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(provideMoshiConverter())
            .client(providesOkHttpClient())
            .build()
    }

    private fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(logging)

            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)


        return client.build()
    }

    private fun provideMoshiConverter(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }



}
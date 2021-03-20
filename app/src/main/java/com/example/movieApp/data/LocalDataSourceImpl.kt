package com.example.movieApp.data

import com.example.myapplication.data.Movie
import com.example.myapplication.data.MoviesResponse
import com.squareup.moshi.Moshi


class LocalDataSourceImpl : LocalStorage {
    override fun getMovies(): List<Movie>? {
        return SessionCachedStorage.moviesResponse?.movies
    }

    override fun saveMovies(moviesResponseAsString: String) {
        if (getMovies() == null) {
            val mosh = Moshi.Builder().build()
            val jsonAdapter = mosh.adapter(MoviesResponse::class.java)
            SessionCachedStorage.moviesResponse = jsonAdapter.fromJson(moviesResponseAsString)
        }
    }

    override fun clearAllLocalStorage() {
        SessionCachedStorage.moviesResponse = null
    }
}
package com.example.movieApp.data

import com.example.myapplication.data.Movie
import com.example.myapplication.data.MoviesResponse


interface LocalStorage {

    fun getMovies(): List<Movie>?
    fun saveMovies(moviesResponseAsString: String)
    fun clearAllLocalStorage()
}

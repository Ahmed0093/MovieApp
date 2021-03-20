package com.example.movieApp.ui.viewModel

import com.example.myapplication.data.Movie
import com.example.myapplication.data.MoviesResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel()
    }


    @Test
    fun ` should return list of four movies with letter a when user input a in search`() {
        val searchInput = "a"
        Assert.assertEquals(
            homeViewModel.getTopFiveMoviesByYear(searchInput, getMockMovies().movies)?.size,
            4
        )

    }

    @Test
    fun ` should return list of four movies with letter a when user input a in search and first movie year is 2018 and title -movie with a 1-`() {
        val searchInput = "a"
        Assert.assertEquals(
            homeViewModel.getTopFiveMoviesByYear(searchInput, getMockMovies().movies)?.size,
            4
        )
        Assert.assertEquals(
            homeViewModel.getTopFiveMoviesByYear(searchInput, getMockMovies().movies)
                ?.get(0)?.title,
            "movie with a 1"
        )
        Assert.assertEquals(
            homeViewModel.getTopFiveMoviesByYear(searchInput, getMockMovies().movies)?.get(0)?.year,
            "2018".toInt()
        )

    }

    @Test
    fun ` should return list of 1 movies when user input -movie with a 1- in search`() {
        val searchInput = "movie with a 1"
        Assert.assertEquals(
            homeViewModel.getTopFiveMoviesByYear(searchInput, getMockMovies().movies)?.size,
            1
        )

    }
}


fun getMockMovies(): MoviesResponse {
    val listOfMovies: ArrayList<Movie> = ArrayList<Movie>()
    val movie1 = Movie(
        title = "movie with a 1",
        year = 2018,
        rating = 5,
        genres = ArrayList(),
        cast = ArrayList()
    )
    val movie2 =
        Movie(title = "my movie", year = 2017, rating = 5, genres = ArrayList(), cast = ArrayList())
    val movie3 = Movie(
        title = "movie with s",
        year = 2015,
        rating = 4,
        genres = ArrayList(),
        cast = ArrayList()
    )
    val movie4 = Movie(
        title = "movie with a 2",
        year = 2015,
        rating = 4,
        genres = ArrayList(),
        cast = ArrayList()
    )
    val movie5 = Movie(
        title = "movie with a 3",
        year = 2015,
        rating = 4,
        genres = ArrayList(),
        cast = ArrayList()
    )
    val movie6 = Movie(
        title = "movie with a 4",
        year = 2012,
        rating = 4,
        genres = ArrayList(),
        cast = ArrayList()
    )
    listOfMovies.add(movie1)
    listOfMovies.add(movie2)
    listOfMovies.add(movie3)
    listOfMovies.add(movie4)
    listOfMovies.add(movie5)
    listOfMovies.add(movie6)

    return MoviesResponse(listOfMovies)
}
package com.example.movieApp.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieApp.repository.FlickerRepo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var flickerRepo: FlickerRepo

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel


    @Before
    fun setUp() {
        flickerRepo = mock { FlickerRepo() }
        movieDetailsViewModel = MovieDetailsViewModel(
            flickerRepo
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun checkgetFlickerImageApiCall() = mainCoroutineRule.runBlockingTest {
        movieDetailsViewModel.getFlickerImage(getMockMovies().movies.get(0))
        verify(flickerRepo, times(1)).getFlickerImgResponse(getMockMovies().movies.get(0).title)
    }
}
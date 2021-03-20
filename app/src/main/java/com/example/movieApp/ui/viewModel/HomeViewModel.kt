package com.example.movieApp.ui.viewModel
import androidx.lifecycle.ViewModel
import com.example.movieApp.data.LocalStorage
import com.example.myapplication.data.Movie
import com.example.myapplication.helper.localStorageProvider
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class HomeViewModel(
    private val localStorage: LocalStorage = localStorageProvider
) : ViewModel() {
    private val TOP_FIVE = 5

    fun getCachedMovies(): List<Movie>? {
        return localStorage.getMovies()
    }

    fun getTopFiveMoviesByYear(queryString: String?, allMovies: List<Movie>?): List<Movie>? {

        return if (queryString == null || queryString.isEmpty())
            allMovies  // Return All Movies if Empty
        else {
            val moviesWithSearchString = allMovies?.filter {
                it.title.toLowerCase(Locale.ROOT).contains(queryString)

            }
            val setOfYearsDescendingOrder =
                getAvailableYearsWithDescendingOrder(moviesWithSearchString)


            getTopFiveFilteredListWithYearCategorized(
                setOfYearsDescendingOrder,
                moviesWithSearchString,
            ).toList()   // Return Top 5 Categorized by Year
        }
    }

    /**
     *
     * [setOfYearsDescendingOrder] a set of current available years
     * to loop on it [2018,2017,2016...] to extract Top 5
     * [moviesWithSearchString] sortedMoviesByYears
     *
     * return [ArrayList<Movie>] list of top 5 for each year ordered by year and rank
     */
    private fun getTopFiveFilteredListWithYearCategorized(
        setOfYearsDescendingOrder: List<Int>,
        moviesWithSearchString: List<Movie>?,
    ): ArrayList<Movie> {
        val filteredResult = ArrayList<Movie>()
        var index = 0
        val maxSlice = TOP_FIVE
        while (index < setOfYearsDescendingOrder.size) {
            //Loop On THe SetOfYears size [ 2018..2017]
            val filteredByYear =
                moviesWithSearchString?.filter { it.year == setOfYearsDescendingOrder[index] }
            val sliceIndex = filteredByYear?.size?.let { getSliceIndex(it, maxSlice) } ?: 0
            var topCurrentYeaList = filteredByYear?.slice(0..sliceIndex)
            topCurrentYeaList =
                if (topCurrentYeaList?.size ?: 0 > 1) topCurrentYeaList?.sortedByDescending { it.rating } else topCurrentYeaList

            topCurrentYeaList?.let { filteredResult.addAll(it) }

            index++

        }
        return filteredResult
    }

    private fun getSliceIndex(currentYearSize: Int, maxSlice: Int) =
        if (currentYearSize > maxSlice) maxSlice - 1 else currentYearSize - 1

    private fun getAvailableYearsWithDescendingOrder(
        sortedMoviesByYear: List<Movie>?,
    ): List<Int> {
        val setOfAvailableYears = HashSet<Int>()
        sortedMoviesByYear?.forEach {
            // Construct A Set Of Available Years for the movies
            setOfAvailableYears.add(it.year)
        }
        return if (setOfAvailableYears.size > 1) setOfAvailableYears.sortedByDescending { it } else setOfAvailableYears.toList()
    }
}

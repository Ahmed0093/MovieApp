package com.example.movieApp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieApp.ui.adapter.MoviesAdapter
import com.example.movieApp.ui.viewModel.HomeViewModel
import com.example.myapplication.R
import com.example.myapplication.data.Movie
import kotlinx.android.synthetic.main.fragment_home_search.*
val EXTRA_MODEL = "EXTRA_MODEL"

class HomeSearchFragment : Fragment() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_home_search,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        moviesAdapter = MoviesAdapter(
            homeViewModel, homeViewModel.getCachedMovies()
        ) {
            onMovieItemClick(
                it
            )
        }

        rvMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.setHasFixedSize(true)
        rvMovies.adapter = moviesAdapter

        initListeners()

    }

    private fun onMovieItemClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_MODEL, movie)
        Navigation.findNavController(this.requireView())
            .navigate(R.id.movie_details, bundle)
    }

    private fun initListeners() {
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Intentionally left blank
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Intentionally left blank
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                moviesAdapter.filter.filter(et_search.text)
            }
        })
    }


}
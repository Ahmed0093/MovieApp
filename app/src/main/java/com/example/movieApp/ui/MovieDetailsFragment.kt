package com.example.movieApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieApp.ui.adapter.FlickerImgAdapter
import com.example.movieApp.ui.viewModel.MovieDetailsViewModel
import com.example.myapplication.R
import com.example.myapplication.data.Movie
import com.example.myapplication.data.Photo
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {
    private var BASE_URL = "https://api.flickr.com/services/rest/"
    private var API_KEY = "a04930c38aed578c9fde8ecca13036c5"
    private lateinit var flickerImgAdapter: FlickerImgAdapter
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_movie_details,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        val bundle = arguments
        if (bundle != null) {
            movie = bundle.getParcelable(EXTRA_MODEL)
            movie?.let { selectedMovie ->
                titleTv.text = selectedMovie.title
                movieYearTv.text = selectedMovie.year.toString()
                rating_bar.rating = selectedMovie.rating.toFloat()
                movieCastTv.text = selectedMovie.cast.joinToString()
                movieGenresTv.text = selectedMovie.genres.joinToString()
                movieDetailsViewModel.getFlickerImage(selectedMovie)
            }
        }
        movieDetailsViewModel.photoListLiveDataUiState.observe(
            viewLifecycleOwner, { flickerPhotoList ->
                if (flickerPhotoList == null) {
                    Toast.makeText(context, "ERROR OCCURED", Toast.LENGTH_SHORT).show()
                } else {
                    initFlickerAdapter(flickerPhotoList)
                }
            })
    }

    private fun initFlickerAdapter(flickerPhotoList: List<Photo>) {
        flickerImgAdapter = FlickerImgAdapter(
            flickerPhotoList
        )


        rvFlicker.layoutManager =
            GridLayoutManager(context, 2)
        rvFlicker.setHasFixedSize(true)
        rvFlicker.adapter = flickerImgAdapter
    }
}
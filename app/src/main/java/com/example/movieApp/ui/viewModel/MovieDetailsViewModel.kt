package com.example.movieApp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieApp.data.LocalStorage
import com.example.movieApp.network.ResultType
import com.example.movieApp.repository.FlickerRepo
import com.example.myapplication.data.FlickerResponse
import com.example.myapplication.data.Movie
import com.example.myapplication.data.Photo
import com.example.myapplication.helper.localStorageProvider
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class MovieDetailsViewModel (
    private val flickerRepo: FlickerRepo = FlickerRepo(),
) : ViewModel() {

    var photoList: List<Photo> = ArrayList()
    private val photoListLiveData = MutableLiveData<List<Photo>>()
    val photoListLiveDataUiState: LiveData<List<Photo>> = photoListLiveData




    fun getFlickerImage(movie: Movie) {
        viewModelScope.launch {
            val result = flickerRepo.getFlickerImgResponse(movieTitle = movie.title)
            when (result) {
                is ResultType.Success -> {
                    if (result.data is FlickerResponse) {
                        photoList = (result.data).photos.photo
                        photoListLiveData.value = photoList
                    }
                }
                    is ResultType.Error -> photoListLiveData.value = null
            }
        }

    }


}

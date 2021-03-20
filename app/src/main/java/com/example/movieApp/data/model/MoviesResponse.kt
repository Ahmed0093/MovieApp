package com.example.myapplication.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MoviesResponse(
    val movies: List<Movie>
)

@Parcelize
data class Movie(
    val cast: List<String>,
    val genres: List<String>,
    val rating: Int,
    val title: String,
    val year: Int
): Parcelable

data class FlickerResponse(
    val photos: Photos,
    val stat: String
)

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: String
)

data class Photo(
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String
)
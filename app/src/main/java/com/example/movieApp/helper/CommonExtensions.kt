package com.example.movieApp.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.myapplication.R

fun Context.getRawResourceAsString(resourceId :Int) :String {
   return this.resources.openRawResource(R.raw.movies)
        .bufferedReader().use { it.readText() }
}

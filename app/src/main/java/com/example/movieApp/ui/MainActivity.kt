package com.example.movieApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.movieApp.helper.getRawResourceAsString
import com.example.myapplication.helper.localStorageProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        localStorageProvider.saveMovies(this.getRawResourceAsString(R.raw.movies))

    }
}
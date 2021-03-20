package com.example.movieApp
import android.app.Application
import com.example.movieApp.helper.getRawResourceAsString
import com.example.myapplication.R
import com.example.myapplication.helper.localStorageProvider

class MovieApp : Application() {

    companion object {
        lateinit var instance: MovieApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
    }

}

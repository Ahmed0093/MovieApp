package com.example.myapplication.helper

import com.example.movieApp.data.LocalDataSourceImpl
import com.example.movieApp.network.NetworkModule

val localStorageProvider by lazy { LocalDataSourceImpl() }

val networkModuleProvider by lazy { NetworkModule() }


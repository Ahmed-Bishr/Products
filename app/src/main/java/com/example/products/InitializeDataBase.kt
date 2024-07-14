package com.example.products

import android.app.Application
import com.example.data.Repository.DataBase.ProductDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InitializeDataBase : Application() {
    override fun onCreate() {
        super.onCreate()
        ProductDataBase.init(this)
    }
}
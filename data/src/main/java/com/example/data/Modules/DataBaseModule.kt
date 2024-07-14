package com.example.data.Repository.Modules

import com.example.data.Repository.DataBase.ProductDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(): ProductDataBase {
        return ProductDataBase.getInstance()
    }
}
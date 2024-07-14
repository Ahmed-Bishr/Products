package com.example.data.Repository.Modules

import com.example.data.Repository.ProductsRepositoryImplementation
import com.example.domain.API.Repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsProductsRepository(impl: ProductsRepositoryImplementation): ProductsRepository
}
package com.example.data.Repository.Modules


import com.example.data.Repository.DataSource.OfflineDataSource.OfflineDataSourceImplementation
import com.example.data.Repository.DataSource.OnlineDataSource.OnlineDataSourceProductsImplementation
import com.example.domain.API.Repository.OfflineDatabase
import com.example.domain.API.Repository.ProductsOnlineDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun ProvidesProducts(impl: OnlineDataSourceProductsImplementation) : ProductsOnlineDataSource {
        return impl
    }

    @Provides
    @Singleton
    fun providesProductsOfflineData(impl: OfflineDataSourceImplementation): OfflineDatabase {
        return impl
    }
}
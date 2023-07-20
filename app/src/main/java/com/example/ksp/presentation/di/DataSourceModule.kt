package com.example.ksp.presentation.di

import com.example.ksp.data.api.KSPApiService
import com.example.ksp.data.repository.datasource.KSPRemoteDataSource
import com.example.ksp.data.repository.datasourceImpl.KSPRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesKSPRemoteDataSource(kspApiService: KSPApiService) : KSPRemoteDataSource{
        return KSPRemoteDataSourceImpl(kspApiService = kspApiService)
    }
}
package com.example.ksp.presentation.di

import com.example.ksp.data.repository.KSPRepositoryImpl
import com.example.ksp.data.repository.datasource.KSPRemoteDataSource
import com.example.ksp.domain.repository.KSPRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideKSPRepository(
        kspRemoteDataSource: KSPRemoteDataSource
    ): KSPRepository {
        return KSPRepositoryImpl(
            kspRemoteDataSource = kspRemoteDataSource
        )
    }
}
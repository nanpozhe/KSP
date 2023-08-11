package com.example.ksp.presentation.di

import com.example.ksp.domain.repository.KSPRepository
import com.example.ksp.domain.usecase.AuthUseCase
import com.example.ksp.domain.usecase.WalletUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(kspRepository: KSPRepository): AuthUseCase{
        return AuthUseCase(kspRepository = kspRepository)
    }

    @Provides
    @Singleton
    fun provideWalletUseCase(kspRepository: KSPRepository): WalletUseCase{
        return WalletUseCase(kspRepository = kspRepository)
    }
}
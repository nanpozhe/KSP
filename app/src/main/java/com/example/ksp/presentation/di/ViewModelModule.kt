package com.example.ksp.presentation.di

import android.app.Application
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.databinding.FragmentCouncilBinding
import com.example.ksp.domain.usecase.AuthUseCase
import com.example.ksp.domain.usecase.WalletUseCase
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import com.example.ksp.presentation.viewmodel.HomeViewModel
import com.example.ksp.presentation.viewmodel.LoginViewModel
import com.example.ksp.presentation.viewmodel.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    @Singleton
    fun providesLogInViewModel(authUseCase: AuthUseCase, sharedPreference: SharedPreference): LoginViewModel{
        return LoginViewModel(authUseCase, sharedPreference)
    }

    @Provides
    @Singleton
    fun providesHomeViewModel(app : Application, sharedPreference: SharedPreference, walletUseCase: WalletUseCase): HomeViewModel{
        return HomeViewModel(app, sharedPreference, walletUseCase)
    }

    @Provides
    @Singleton
    fun providesSplashViewModel(sharedPreference: SharedPreference): SplashViewModel{
        return SplashViewModel(sharedPreference)
    }
}
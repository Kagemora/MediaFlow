package com.kagemora.mediaflow.feature.auth.impl.di

import com.kagemora.mediaflow.feature.auth.api.AuthApi
import com.kagemora.mediaflow.feature.auth.impl.data.remote.AuthService
import com.kagemora.mediaflow.feature.auth.impl.data.repository.AuthApiImpl
import com.kagemora.mediaflow.feature.auth.impl.data.repository.AuthRepositoryImpl
import com.kagemora.mediaflow.feature.auth.impl.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
interface AuthModule {
    @Binds
    @Singleton
    fun bindAuthApi(impl: AuthApiImpl): AuthApi

    @Binds
    @Singleton
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    companion object {
        @Provides
        @Singleton
        fun provideAuthService(retrofit: Retrofit): AuthService =
            retrofit.create(AuthService::class.java)
    }
}
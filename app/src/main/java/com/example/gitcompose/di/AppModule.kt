package com.example.gitcompose.di

import com.example.gitcompose.utils.ArgsKeeper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArgsKeeper() = ArgsKeeper()
}
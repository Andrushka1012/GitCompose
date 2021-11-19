package com.example.gitcompose.infrastructure.networking

import com.example.gitcompose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSecretInterceptor() = SecretInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(secretInterceptor: SecretInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(secretInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubService(retrofit: Retrofit): GitHubService =
        retrofit.create(GitHubService::class.java)

}
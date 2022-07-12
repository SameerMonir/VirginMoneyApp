package com.example.virginmoneyapp.Main


import com.example.virginmoneyapp.Api.ApiService
import com.example.virginmoneyapp.model.Repository
import com.example.virginmoneyapp.model.RepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/ "

@Module
@InstallIn(SingletonComponent ::class)
class ServiceModel {

    @Provides
    fun provideApiService(): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    fun provideRepositoryLayer(service: ApiService) : Repository =
        RepositoryImpl(service)

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
package ru.noosle.harry_potter_mvi.ui.main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.noosle.harry_potter_mvi.ui.main.dao.HogwartsRepository
import ru.noosle.harry_potter_mvi.ui.main.dao.HogwartsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesBaseUrl(): String = "https://hp-api.onrender.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideHogwartsService(retrofit: Retrofit): HogwartsService = retrofit.create(HogwartsService::class.java)

    @Provides
    @Singleton
    fun provideHogwartsRepository(hogwartsService: HogwartsService): HogwartsRepository =
        HogwartsRepository(hogwartsService)
}
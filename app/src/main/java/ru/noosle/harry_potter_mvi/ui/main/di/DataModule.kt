package ru.noosle.harry_potter_mvi.ui.main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.noosle.harry_potter_mvi.ui.main.dao.HogwartsRepository
import ru.noosle.harry_potter_mvi.ui.main.dao.HogwartsService

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun provideHogwartsRepository(): HogwartsRepository {
        return HogwartsRepository(HogwartsService.getInstance())
    }
}
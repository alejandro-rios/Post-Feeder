package com.alejandrorios.gpostfeeder.di

import com.alejandrorios.data.di.RepositoryModuleData
import com.alejandrorios.domain.interactor.GetFeedsInteractor
import com.alejandrorios.domain.interactor.Interactor
import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.domain.repository.FeedsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Module(includes = [com.alejandrorios.data.di.RepositoryModuleData::class])
class InteractorsModule {

    @Singleton
    @Provides
    fun provideGetFeedsInteractor(
        feedsRepository: com.alejandrorios.domain.repository.FeedsRepository
    ): com.alejandrorios.domain.interactor.Interactor<List<com.alejandrorios.domain.model.Feeds>, Unit> =
        com.alejandrorios.domain.interactor.GetFeedsInteractor(feedsRepository)
}

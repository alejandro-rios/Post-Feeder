package com.alejandrorios.gpostfeeder.di

import com.alejandrorios.gpostfeeder.data.di.RepositoryModuleData
import com.alejandrorios.gpostfeeder.domain.Interactor
import com.alejandrorios.gpostfeeder.domain.interactor.GetFeedsInteractor
import com.alejandrorios.gpostfeeder.domain.model.Feeds
import com.alejandrorios.gpostfeeder.domain.repository.FeedsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Module(includes = [RepositoryModuleData::class])
class InteractorsModule {

    @Singleton
    @Provides
    fun provideGetFeedsInteractor(
        feedsRepository: FeedsRepository
    ): Interactor<List<Feeds>, Unit> = GetFeedsInteractor(feedsRepository)
}

package com.alejandrorios.gpostfeeder.data.di

import com.alejandrorios.gpostfeeder.data.network.FeedsService
import com.alejandrorios.gpostfeeder.data.repository.FeedsRepositoryImpl
import com.alejandrorios.gpostfeeder.domain.repository.FeedsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Module(includes = [ServiceProviderModule::class])
class RepositoryModuleData {

    @Provides
    @Singleton
    fun provideFeedsRepository(
        feedsService: FeedsService
    ): FeedsRepository {
        return FeedsRepositoryImpl(feedsService)
    }
}

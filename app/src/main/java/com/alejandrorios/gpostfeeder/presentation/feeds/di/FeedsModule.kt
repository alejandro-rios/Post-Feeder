package com.alejandrorios.gpostfeeder.presentation.feeds.di

import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsContract
import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Module
class FeedsModule {

    @Provides
    fun provideFeedsPresenter(presenter: FeedsPresenter): FeedsContract.Presenter = presenter
}

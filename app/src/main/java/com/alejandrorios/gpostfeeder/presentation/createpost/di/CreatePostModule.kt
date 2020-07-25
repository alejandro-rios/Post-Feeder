package com.alejandrorios.gpostfeeder.presentation.createpost.di

import com.alejandrorios.gpostfeeder.presentation.createpost.CreatePostContract
import com.alejandrorios.gpostfeeder.presentation.createpost.CreatePostPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Module
class CreatePostModule {

    @Provides
    fun provideCreatePostPresenter(presenter: CreatePostPresenter): CreatePostContract.Presenter =
        presenter
}

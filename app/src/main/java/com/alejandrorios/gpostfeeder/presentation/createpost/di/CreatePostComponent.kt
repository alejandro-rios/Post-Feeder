package com.alejandrorios.gpostfeeder.presentation.createpost.di

import com.alejandrorios.gpostfeeder.di.AppComponent
import com.alejandrorios.gpostfeeder.presentation.createpost.CreatePostActivity
import dagger.Component

/**
 * Created by Alejandro Rios on 7/25/20
 */
@com.alejandrorios.data.di.ActivityScope
@Component(dependencies = [AppComponent::class], modules = [CreatePostModule::class])
interface CreatePostComponent {
    fun inject(activity: CreatePostActivity)
}

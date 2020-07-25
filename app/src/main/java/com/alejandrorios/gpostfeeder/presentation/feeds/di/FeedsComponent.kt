package com.alejandrorios.gpostfeeder.presentation.feeds.di

import com.alejandrorios.gpostfeeder.di.AppComponent
import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsActivity
import dagger.Component

/**
 * Created by Alejandro Rios on 7/25/20
 */
@com.alejandrorios.data.di.ActivityScope
@Component(dependencies = [AppComponent::class], modules = [FeedsModule::class])
interface FeedsComponent {
    fun inject(activity: FeedsActivity)
}

package com.alejandrorios.gpostfeeder.presentation.feeds.di

import com.alejandrorios.gpostfeeder.data.di.ActivityScope
import com.alejandrorios.gpostfeeder.di.AppComponent
import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsActivity
import dagger.Component

/**
 * Created by Alejandro Rios on 7/25/20
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [FeedsModule::class])
interface FeedsComponent {
    fun inject(activity: FeedsActivity)
}

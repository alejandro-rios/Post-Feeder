package com.alejandrorios.gpostfeeder.di

import android.content.Context
import com.alejandrorios.domain.interactor.Interactor
import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.gpostfeeder.GorillaPostFeederApplication
import com.alejandrorios.gpostfeeder.utils.CoroutinesContextProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (InteractorsModule::class)]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Context): Builder
    }

    fun inject(gorillaPostFeederApplication: GorillaPostFeederApplication)

    fun provideIOCoroutinesContextProvider(): CoroutinesContextProvider

    fun provideGetFeedsInteractor(): Interactor<List<Feeds>, Unit>
}

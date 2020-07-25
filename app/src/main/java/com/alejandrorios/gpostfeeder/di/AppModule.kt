package com.alejandrorios.gpostfeeder.di

import com.alejandrorios.gpostfeeder.utils.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContextProvider(): CoroutinesContextProvider {
        return CoroutinesContextProvider(Dispatchers.Main, Dispatchers.IO)
    }
}

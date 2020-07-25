package com.alejandrorios.gpostfeeder.data.di

import com.alejandrorios.gpostfeeder.data.network.FeedsService
import com.alejandrorios.gpostfeeder.utils.TIMEOUT_SECONDS
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Module
class ServiceProviderModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideBaseClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideBaseRetrofitCoroutines(baseOkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gl-endpoint.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(baseOkHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideFeedsService(
        retrofit: Retrofit
    ): FeedsService {
        return retrofit.create(FeedsService::class.java)
    }
}

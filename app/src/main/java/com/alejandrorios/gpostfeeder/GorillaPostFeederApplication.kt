package com.alejandrorios.gpostfeeder

import android.app.Application
import android.content.Context
import com.alejandrorios.gpostfeeder.di.AppComponent
import com.alejandrorios.gpostfeeder.di.DaggerAppComponent

/**
 * Created by Alejandro Rios on 7/25/20
 */
class GorillaPostFeederApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupDagger()
    }

    private fun setupDagger() {
        component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
    }

    fun getAppComponent(context: Context): AppComponent {
        return (context.applicationContext as GorillaPostFeederApplication).component
    }

    companion object {
        lateinit var instance: GorillaPostFeederApplication private set
    }
}

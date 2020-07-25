package com.alejandrorios.gpostfeeder.presentation.createpost

import com.alejandrorios.gpostfeeder.GorillaPostFeederApplication
import com.alejandrorios.gpostfeeder.presentation.base.BaseActivity
import com.alejandrorios.gpostfeeder.presentation.createpost.di.CreatePostComponent
import com.alejandrorios.gpostfeeder.presentation.createpost.di.CreatePostModule
import com.alejandrorios.gpostfeeder.presentation.createpost.di.DaggerCreatePostComponent

/**
 * Created by Alejandro Rios on 7/25/20
 */
abstract class BaseCreatePostActivity : BaseActivity() {

    abstract fun injectActivityBuilder(builder: CreatePostComponent)

    override fun prepareActivityBuilder() {
        injectActivityBuilder(
            DaggerCreatePostComponent.builder()
                .appComponent(GorillaPostFeederApplication.instance.getAppComponent(this))
                .createPostModule(CreatePostModule())
                .build()
        )
    }
}

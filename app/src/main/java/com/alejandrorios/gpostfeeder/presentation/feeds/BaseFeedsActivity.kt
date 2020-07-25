package com.alejandrorios.gpostfeeder.presentation.feeds

import com.alejandrorios.gpostfeeder.GorillaPostFeederApplication
import com.alejandrorios.gpostfeeder.presentation.base.BaseActivity
import com.alejandrorios.gpostfeeder.presentation.feeds.di.DaggerFeedsComponent
import com.alejandrorios.gpostfeeder.presentation.feeds.di.FeedsComponent
import com.alejandrorios.gpostfeeder.presentation.feeds.di.FeedsModule

/**
 * Created by Alejandro Rios on 7/25/20
 */
abstract class BaseFeedsActivity : BaseActivity() {

    abstract fun injectActivityBuilder(builder: FeedsComponent)

    override fun prepareActivityBuilder() {
        injectActivityBuilder(
            DaggerFeedsComponent.builder()
                .appComponent(GorillaPostFeederApplication.instance.getAppComponent(this))
                .feedsModule(FeedsModule())
                .build()
        )
    }
}

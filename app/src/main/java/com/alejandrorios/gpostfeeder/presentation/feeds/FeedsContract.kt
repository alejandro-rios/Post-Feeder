package com.alejandrorios.gpostfeeder.presentation.feeds

import com.alejandrorios.gpostfeeder.domain.model.Feeds
import com.alejandrorios.gpostfeeder.presentation.base.BasePresenter
import com.alejandrorios.gpostfeeder.presentation.base.BaseView

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface FeedsContract {

    interface View : BaseView {

        fun showLoading()

        fun hideLoading()

        fun loadFeeds(feeds: List<Feeds>)
    }

    interface Presenter : BasePresenter<View> {
        fun getFeeds()
    }
}

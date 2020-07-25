package com.alejandrorios.gpostfeeder.presentation.createpost

import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.gpostfeeder.presentation.base.BasePresenter
import com.alejandrorios.gpostfeeder.presentation.base.BaseView

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface CreatePostContract {

    interface View : BaseView {

        fun showError(message: String)

        fun gotToFeeds(feed: Feeds)
    }

    interface Presenter : BasePresenter<View> {

        fun savePost(post: String, imagePath: String?)
    }
}

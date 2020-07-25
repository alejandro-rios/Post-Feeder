package com.alejandrorios.gpostfeeder.presentation.feeds

import com.alejandrorios.domain.interactor.Interactor
import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.gpostfeeder.utils.CoroutinesContextProvider
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * Created by Alejandro Rios on 7/25/20
 */
class FeedsPresenter @Inject constructor(
    override val coroutinesContextProvider: CoroutinesContextProvider,
    private val getFeedsInteractor: Interactor<List<Feeds>, Unit>
) : FeedsContract.Presenter {

    override val parentJob = Job()
    override var view: FeedsContract.View? = null

    override fun getFeeds() {
        view?.showLoading()
        launchJob {
            try {
                val feeds = withContext(coroutinesContextProvider.backgroundContext) {
                    getFeedsInteractor(Unit)
                }

                view?.run {
                    hideLoading()
                    loadFeeds(feeds)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                view?.hideLoading()
            }
        }
    }
}

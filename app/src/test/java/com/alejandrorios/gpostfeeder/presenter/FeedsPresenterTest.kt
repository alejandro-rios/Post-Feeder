package com.alejandrorios.gpostfeeder.presenter

import com.alejandrorios.domain.interactor.Interactor
import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.gpostfeeder.BasePresenterTest
import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsContract
import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsPresenter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 7/25/20
 */
class FeedsPresenterTest : BasePresenterTest() {

    @MockK
    lateinit var getFeedsInteractor: com.alejandrorios.domain.interactor.Interactor<List<com.alejandrorios.domain.model.Feeds>, Unit>

    @MockK
    lateinit var view: FeedsContract.View

    private lateinit var presenter: FeedsContract.Presenter
    private val feedList = listOf(
        com.alejandrorios.domain.model.Feeds(
            id = 1,
            firstName = "Averill",
            lastName = "Erricker",
            postBody = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.",
            unixTimestamp = "1525940534",
            image = "http://dummyimage.com/400x200.png/cc0000/ffffff"
        )
    )

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Before
    fun setUp() {
        presenter = FeedsPresenter(
            coroutinesContextProvider,
            getFeedsInteractor
        )

        coEvery {
            getFeedsInteractor(Unit)
        } answers {
            feedList
        }
    }

    @After
    fun clear() {
        presenter.unBind()
    }

    @Test
    fun `given presenter when getFeeds it's called then should return a list of Feeds`() {
        // Given
        presenter.bind(view)

        // Whenever
        presenter.getFeeds()

        // Then
        coVerifyOrder {
            view.showLoading()
            getFeedsInteractor(Unit)
            view.hideLoading()
            view.loadFeeds(feedList)
        }
    }
}

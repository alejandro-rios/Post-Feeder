package com.alejandrorios.gpostfeeder.presenter

import com.alejandrorios.gpostfeeder.BasePresenterTest
import com.alejandrorios.gpostfeeder.presentation.createpost.CreatePostContract
import com.alejandrorios.gpostfeeder.presentation.createpost.CreatePostPresenter
import com.alejandrorios.gpostfeeder.utils.DEFAULT_LAST_NAME
import com.alejandrorios.gpostfeeder.utils.DEFAULT_NAME
import com.alejandrorios.gpostfeeder.utils.IMAGE_EMPTY_MESSAGE
import com.alejandrorios.gpostfeeder.utils.POST_EMPTY_MESSAGE
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.verify
import java.util.*
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 7/25/20
 */
class CreatePostPresenterTest : BasePresenterTest() {

    @MockK
    lateinit var view: CreatePostContract.View

    private lateinit var presenter: CreatePostContract.Presenter
    private val now = 1550160535168L

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Before
    fun setUp() {
        mockkStatic(Calendar::class)

        every { Calendar.getInstance().timeInMillis } returns now
    }

    @After
    fun clear() {
        presenter.unBind()
    }

    @Test
    fun `given presenter when savePost it's called then should go to FeedsActivity`() {
        // Given
        presenter = CreatePostPresenter(coroutinesContextProvider)
        presenter.bind(view)

        // Whenever
        presenter.savePost(
            "Some post",
            "Some image path"
        )

        // Then
        verify(exactly = 1) {
            view.gotToFeeds(
                com.alejandrorios.domain.model.Feeds(
                    id = 0,
                    firstName = DEFAULT_NAME,
                    lastName = DEFAULT_LAST_NAME,
                    postBody = "Some post",
                    unixTimestamp = now.toString(),
                    image = "Some image path"
                )
            )
        }
        verify(exactly = 0) {
            view.showError(POST_EMPTY_MESSAGE)
            view.showError(IMAGE_EMPTY_MESSAGE)
        }
    }

    @Test
    fun `given presenter when savePost it's called with post empty then should show an error message`() {
        // Given
        presenter = CreatePostPresenter(coroutinesContextProvider)
        presenter.bind(view)

        // Whenever
        presenter.savePost(
            "",
            "Some image path"
        )

        // Then
        verify(exactly = 1) {
            view.showError(POST_EMPTY_MESSAGE)
        }
        verify(exactly = 0) {
            view.showError(IMAGE_EMPTY_MESSAGE)
            view.gotToFeeds(any())
        }
    }

    @Test
    fun `given presenter when savePost it's called with image path empty then should show an error message`() {
        // Given
        presenter = CreatePostPresenter(coroutinesContextProvider)
        presenter.bind(view)

        // Whenever
        presenter.savePost(
            "Some post",
            ""
        )

        // Then
        verify(exactly = 1) {
            view.showError(IMAGE_EMPTY_MESSAGE)
        }
        verify(exactly = 0) {
            view.showError(POST_EMPTY_MESSAGE)
            view.gotToFeeds(any())
        }
    }
}

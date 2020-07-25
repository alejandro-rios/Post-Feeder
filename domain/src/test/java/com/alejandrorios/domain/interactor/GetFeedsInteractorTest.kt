package com.alejandrorios.domain.interactor

import com.alejandrorios.domain.MockableTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

/**
 * Created by Alejandro Rios on 7/25/20
 */
class GetFeedsInteractorTest : MockableTest {

    @MockK
    lateinit var feedsRepository: com.alejandrorios.domain.repository.FeedsRepository

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            feedsRepository.getFeeds()
        } answers {
            listOf(
                com.alejandrorios.domain.model.Feeds(
                    id = 1,
                    firstName = "Averill",
                    lastName = "Erricker",
                    postBody = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.",
                    unixTimestamp = "1525940534",
                    image = "http://dummyimage.com/400x200.png/cc0000/ffffff"
                )
            )
        }
    }

    @Test
    fun `given interactor when is invoked then should return a list of Feeds`() {
        // GIVEN
        val interactor = com.alejandrorios.domain.interactor.GetFeedsInteractor(
            feedsRepository
        )

        // WHEN
        val result =
            runBlocking {
                interactor(Unit)
            }

        // THEN
        with(result.first()) {
            this.id shouldBeEqualTo 1
            this.firstName shouldBeEqualTo "Averill"
            this.lastName shouldBeEqualTo "Erricker"
            this.postBody shouldBeEqualTo "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl."
            this.unixTimestamp shouldBeEqualTo "1525940534"
            this.image shouldBeEqualTo "http://dummyimage.com/400x200.png/cc0000/ffffff"
        }

        coVerify {
            feedsRepository.getFeeds()
        }
    }
}

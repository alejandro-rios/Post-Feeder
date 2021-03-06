package com.alejandrorios.gpostfeeder.repository

import com.alejandrorios.gpostfeeder.MockableTest
import com.alejandrorios.gpostfeeder.data.entities.APIFeeds
import com.alejandrorios.gpostfeeder.data.network.FeedsService
import com.alejandrorios.gpostfeeder.data.repository.FeedsRepositoryImpl
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
class FeedsRepositoryTest : MockableTest {

    @MockK
    lateinit var feedsService: FeedsService

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            feedsService.getFeeds()
        } answers {
            listOf(
                APIFeeds(
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
    fun `given repository when getFeeds it's called then should get a list of APIFeeds`() {
        val repository = FeedsRepositoryImpl(feedsService)

        val result =
            runBlocking {
                repository.getFeeds()
            }

        with(result.first()) {
            this.id shouldBeEqualTo 1
            this.firstName shouldBeEqualTo "Averill"
            this.lastName shouldBeEqualTo "Erricker"
            this.postBody shouldBeEqualTo "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl."
            this.unixTimestamp shouldBeEqualTo "1525940534"
            this.image shouldBeEqualTo "http://dummyimage.com/400x200.png/cc0000/ffffff"
        }

        coVerify {
            feedsService.getFeeds()
        }
    }
}

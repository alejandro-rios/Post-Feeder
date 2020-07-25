package com.alejandrorios.data.mapper

import com.alejandrorios.data.entities.APIFeeds
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

/**
 * Created by Alejandro Rios on 7/25/20
 */
class APIFeedsMapperTest {
    @Test
    fun `given mapper when map it's called then should get Feeds from APIFeeds`() {
        // GIVEN
        val apiFeedsSource =
            APIFeeds(
                id = 1,
                firstName = "Averill",
                lastName = "Erricker",
                postBody = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.",
                unixTimestamp = "1525940534",
                image = "http://dummyimage.com/400x200.png/cc0000/ffffff"
            )

        // WHEN
        val result = apiFeedsSource.toFeeds()

        // THEN
        result.id shouldBeEqualTo 1
        result.firstName shouldBeEqualTo "Averill"
        result.lastName shouldBeEqualTo "Erricker"
        result.postBody shouldBeEqualTo "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl."
        result.unixTimestamp shouldBeEqualTo "1525940534"
        result.image shouldBeEqualTo "https://dummyimage.com/400x200.png/cc0000/ffffff"
    }
}

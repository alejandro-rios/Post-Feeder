package com.alejandrorios.gpostfeeder.data.entities

import com.alejandrorios.gpostfeeder.domain.model.Feeds
import com.google.gson.annotations.SerializedName

/**
 * Created by Alejandro Rios on 7/25/20
 */
data class APIFeeds(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("post_body")
    val postBody: String,
    @SerializedName("unix_timestamp")
    val unixTimestamp: String,
    val image: String?
) {
    fun toFeeds(): Feeds {
        return Feeds(
            this.id,
            this.firstName,
            this.lastName,
            this.postBody,
            this.unixTimestamp,
            this.image?.replace("http", "https")
        )
    }
}

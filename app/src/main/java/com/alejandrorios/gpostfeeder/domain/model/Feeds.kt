package com.alejandrorios.gpostfeeder.domain.model

/**
 * Created by Alejandro Rios on 7/25/20
 */
data class Feeds(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val postBody: String,
    val unixTimestamp: String,
    val image: String?
)

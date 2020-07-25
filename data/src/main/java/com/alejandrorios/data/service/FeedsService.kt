package com.alejandrorios.data.service

import com.alejandrorios.data.entities.APIFeeds
import retrofit2.http.GET

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface FeedsService {

    @GET("feed")
    suspend fun getFeeds(): List<APIFeeds>
}

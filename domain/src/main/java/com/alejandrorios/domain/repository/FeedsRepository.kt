package com.alejandrorios.domain.repository

import com.alejandrorios.domain.model.Feeds

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface FeedsRepository {

    suspend fun getFeeds(): List<com.alejandrorios.domain.model.Feeds>
}

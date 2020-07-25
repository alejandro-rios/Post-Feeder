package com.alejandrorios.gpostfeeder.domain.repository

import com.alejandrorios.gpostfeeder.domain.model.Feeds

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface FeedsRepository {

    suspend fun getFeeds(): List<Feeds>
}

package com.alejandrorios.gpostfeeder.data.repository

import com.alejandrorios.gpostfeeder.data.network.FeedsService
import com.alejandrorios.gpostfeeder.domain.model.Feeds
import com.alejandrorios.gpostfeeder.domain.repository.FeedsRepository

/**
 * Created by Alejandro Rios on 7/25/20
 */
class FeedsRepositoryImpl(private var feedsService: FeedsService) : FeedsRepository {

    override suspend fun getFeeds(): List<Feeds> {
        return feedsService.getFeeds().map {
            it.toFeeds()
        }
    }
}

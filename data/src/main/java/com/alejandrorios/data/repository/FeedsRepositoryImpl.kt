package com.alejandrorios.data.repository

import com.alejandrorios.data.service.FeedsService
import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.domain.repository.FeedsRepository

/**
 * Created by Alejandro Rios on 7/25/20
 */
class FeedsRepositoryImpl(private var feedsService: com.alejandrorios.data.service.FeedsService) :
    com.alejandrorios.domain.repository.FeedsRepository {

    override suspend fun getFeeds(): List<com.alejandrorios.domain.model.Feeds> {
        return feedsService.getFeeds().map {
            it.toFeeds()
        }
    }
}

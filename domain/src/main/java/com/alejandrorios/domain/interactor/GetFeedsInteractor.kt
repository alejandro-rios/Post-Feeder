package com.alejandrorios.domain.interactor

import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.domain.repository.FeedsRepository

/**
 * Created by Alejandro Rios on 7/25/20
 */
class GetFeedsInteractor(private val feedsRepository: FeedsRepository) :
    Interactor<List<Feeds>, Unit> {

    override suspend fun invoke(params: Unit): List<Feeds> {
        return feedsRepository.getFeeds()
    }
}

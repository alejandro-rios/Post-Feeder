package com.alejandrorios.gpostfeeder.domain.interactor

import com.alejandrorios.gpostfeeder.domain.Interactor
import com.alejandrorios.gpostfeeder.domain.model.Feeds
import com.alejandrorios.gpostfeeder.domain.repository.FeedsRepository

/**
 * Created by Alejandro Rios on 7/25/20
 */
class GetFeedsInteractor(private val feedsRepository: FeedsRepository) :
    Interactor<List<Feeds>, Unit> {

    override suspend fun invoke(params: Unit): List<Feeds> {
        return feedsRepository.getFeeds()
    }
}

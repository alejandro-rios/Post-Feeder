package com.alejandrorios.gpostfeeder.domain

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface Interactor<Response, Params> where Response : Any {
    suspend operator fun invoke(
        params: Params
    ): Response
}

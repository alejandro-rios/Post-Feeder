package com.alejandrorios.gpostfeeder.utils

import kotlin.coroutines.CoroutineContext

/**
 * Created by Alejandro Rios on 7/25/20
 */
data class CoroutinesContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)

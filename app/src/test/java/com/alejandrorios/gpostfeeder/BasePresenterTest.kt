package com.alejandrorios.gpostfeeder

import com.alejandrorios.gpostfeeder.utils.CoroutinesContextProvider
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Alejandro Rios on 7/25/20
 */
abstract class BasePresenterTest {

    protected val coroutinesContextProvider =
        CoroutinesContextProvider(CoroutineContextForTest, CoroutineContextForTest)

    object CoroutineContextForTest : CoroutineDispatcher() {

        @ExperimentalCoroutinesApi
        override fun isDispatchNeeded(context: CoroutineContext): Boolean = false

        override fun dispatch(context: CoroutineContext, block: Runnable) {
            block.run()
        }
    }
}

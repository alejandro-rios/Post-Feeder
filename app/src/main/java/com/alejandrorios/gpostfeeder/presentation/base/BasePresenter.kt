package com.alejandrorios.gpostfeeder.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.alejandrorios.gpostfeeder.utils.CoroutinesContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface BasePresenter<View : BaseView> : LifecycleObserver {

    var view: View?
    val parentJob: Job
    val coroutinesContextProvider: CoroutinesContextProvider

    fun bind(view: View) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unBind() {
        view = null
        parentJob.apply {
            cancelChildren()
        }
    }

    fun launchJob(job: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(coroutinesContextProvider.mainContext + parentJob).launch {
            job()
        }
    }
}

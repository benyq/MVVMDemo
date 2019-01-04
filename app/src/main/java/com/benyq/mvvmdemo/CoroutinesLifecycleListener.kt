package com.benyq.mvvmdemo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.Deferred

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2018/12/26
 */
class CoroutinesLifecycleListener(val deferred: Deferred<*>) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cancelCoroutine() {
        if (!deferred.isCancelled) {
            deferred.cancel()
        }
    }
}
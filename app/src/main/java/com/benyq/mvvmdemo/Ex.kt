package com.benyq.mvvmdemo

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Scheduler
import io.reactivex.android.MainThreadDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/2
 */


fun <T> LifecycleOwner.doBackGround(loader: suspend () -> T): Job{
    return GlobalScope.launch(context = Dispatchers.Main) {
        loader()
    }
}

fun <T> LifecycleOwner.load(loader: () -> T): Deferred<T>{
    val deferred = GlobalScope.async(context = Dispatchers.Default) {
        loader()
    }
    lifecycle.addObserver(CoroutinesLifecycleListener(deferred))
    return deferred
}

fun <T> Deferred<T>.then(block: (T) -> Unit): Job {
    return GlobalScope.launch(context = Dispatchers.Main) {
        block(this@then.await())
    }
}

fun <T> LiveData<T>.toReactiveStream(): Flowable<T> = Flowable
        .create({ emitter: FlowableEmitter<T> ->
            val observer = Observer<T> { data ->
                data?.let {
                    emitter.onNext(it)
                }
            }
            observeForever(observer)
            emitter.setCancellable {
                object : MainThreadDisposable() {
                    override fun onDispose() {
                        removeObserver(observer)
                    }
                }
            }
        }, BackpressureStrategy.LATEST)
//        .subscribeOn(AndroidSchedulers.mainThread())
//        .observeOn(AndroidSchedulers.mainThread())
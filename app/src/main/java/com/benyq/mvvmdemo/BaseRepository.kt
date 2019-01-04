package com.benyq.mvvmdemo

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/3
 */
abstract class BaseRepository {

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun addSubscribe(subscription: Disposable) {
        mCompositeDisposable.add(subscription)
    }

    fun unSubscribe() {
        mCompositeDisposable.dispose()
    }
}
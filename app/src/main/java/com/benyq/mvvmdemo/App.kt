package com.benyq.mvvmdemo

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/4
 */
class App: Application(), KodeinAware{

    override val kodein = Kodein.lazy {
        /* bindings */
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
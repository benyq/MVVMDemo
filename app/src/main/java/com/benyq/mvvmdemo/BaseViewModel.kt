package com.benyq.mvvmdemo

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.lang.reflect.ParameterizedType


/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/3
 */
class BaseViewModel<T : BaseRepository>(@NonNull application: Application): AndroidViewModel(application) {

    lateinit var mRepository: T

    init {
    }

    override fun onCleared() {
        super.onCleared()
        mRepository.unSubscribe()
    }

}
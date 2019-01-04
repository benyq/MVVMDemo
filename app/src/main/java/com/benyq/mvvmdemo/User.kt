package com.benyq.mvvmdemo

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/2
 */
class User{
    var name: ObservableField<String> = ObservableField()
    var age: ObservableField<Int> = ObservableField()
}
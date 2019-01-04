package com.benyq.mvvmdemo

import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/2
 */
class UserProfileViewModel: ViewModel() {

    var name = MutableLiveData<String>()
    var age =  MutableLiveData<Int>()

    var persons = MutableLiveData<List<Person>>()

    fun init(){
        name.value = "未显示"
        age.value = 0
    }

    fun freshUserInfoOnClick(view: View){
        GlobalScope.launch(Dispatchers.Default) {
            delay(3000)
            launch(Dispatchers.Main){
                val person = Person("susan&&benyq", 22)
                name.postValue(person.name)
                age.postValue(person.age)
                val list = mutableListOf<Person>()
                list.add(Person("susan", 22))
                list.add(Person("benyq", 22))
                list.add(Person("zxx", 56))
                persons.postValue(list)
            }
        }
    }

}
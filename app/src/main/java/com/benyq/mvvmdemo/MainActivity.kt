package com.benyq.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.benyq.mvvmdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
        val user = User()
        with(user){
            age.set(22)
            name.set("benyq")
        }
        //向布局文件中的variable设置变量
        activityMainBinding.user = user
        val handler = Handler()
        activityMainBinding.handlers = handler
        activityMainBinding.executePendingBindings()
        Log.e("benyq", "start1")
        GlobalScope.launch(Dispatchers.Default) {
            while (isActive){
                delay(1000)
                launch(Dispatchers.Main) {
                    var newAge = user.age.get()?.plus(1)
                    user.age.set(newAge)
                }
            }
        }
        Log.e("benyq", "start2")
        handler.job = doBackGround{
            while (true){
                delay(2000)
                Log.e("benyq", "doBackGround")
            }
        }
    }

    fun onClick(view: View){
        Log.e("benyq", "click")
        job.cancel()
    }
}

package com.benyq.mvvmdemo

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.Job

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/2
 */
class Handler {

    var job: Job? = null

    fun onClick(view: View){
        job?.cancel()
        Toast.makeText(view.context, "show", Toast.LENGTH_SHORT).show()
        Intent(view.context, UserActivity::class.java).let {
            view.context.startActivity(it)
        }

    }
}
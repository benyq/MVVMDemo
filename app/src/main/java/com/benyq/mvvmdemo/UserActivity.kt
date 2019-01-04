package com.benyq.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.benyq.mvvmdemo.databinding.ActivityUserBinding
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.MainThreadDisposable
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private val mAdapter by lazy { PersonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityUserBinding = DataBindingUtil.setContentView<ActivityUserBinding>(this, R.layout.activity_user)
        val userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        userProfileViewModel.init()
        activityUserBinding.let {
            it.userModel = userProfileViewModel
            it.setLifecycleOwner(this)
        }
        rvPerson.run {
            layoutManager = LinearLayoutManager(this@UserActivity)
            adapter = mAdapter
        }
//        userProfileViewModel.persons.observe(this, Observer {
//            mAdapter.addNewData(it.toMutableList())
//        })

        userProfileViewModel.persons.toReactiveStream()
                .autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))//OnDestory时自动解绑
                .subscribe({
                    mAdapter.addNewData(it.toMutableList())
                }, {
                    Log.e("benyq", it.message)
                })


    }
}

package com.benyq.mvvmdemo

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benyq.benyqwanandroid.base.adapter.BaseAdapter
import com.benyq.benyqwanandroid.base.adapter.BaseHolder

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/3
 */
class PersonAdapter: BaseAdapter<Person>(R.layout.item_person) {
    override fun convert(holder: BaseHolder, position: Int, bean: Person) {
        holder.setText(R.id.tvName, bean.name)
                .setText(R.id.tvAge, bean.age.toString())
    }
}
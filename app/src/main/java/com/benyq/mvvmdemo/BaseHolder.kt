package com.benyq.benyqwanandroid.base.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.util.SparseArray
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView


/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2018/11/29
 */
open class BaseHolder(baseView: View): RecyclerView.ViewHolder(baseView) {

    private val mViews = SparseArray<View>()
    private val mLayoutId: Int = 0
    val listenerIds = mutableSetOf<Int>()

    fun <V : View> getView(viewId: Int): V {
        val view = mViews.get(viewId) ?: itemView.findViewById(viewId)
        mViews.put(viewId, view)
        return view as V
    }

    fun setText(@IdRes viewId: Int, text: String): BaseHolder {
        val view: TextView = getView(viewId)
        view.text = text
        return this
    }

    fun setImageRes(@IdRes viewId: Int, res: Drawable): BaseHolder {
        val view: ImageView = getView(viewId)
        view.setImageDrawable(res)
        return this
    }

    fun setImageRes(@IdRes viewId: Int, @DrawableRes res: Int): BaseHolder {
        val view: ImageView = getView(viewId)
        view.setImageResource(res)
        return this
    }
}
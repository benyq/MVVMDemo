package com.benyq.mvvmdemo

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *@author benyq
 *@e-mail 1520063035@qq.com
 *@Date 2019/1/3
 */
interface ApiService {

    companion object {
        const val baseUrl = "http://wanandroid.com"
    }


    /**
     * 首页文章
     */
    @GET("/article/list/{id}/json")
    fun getHomeArticles(@Path("id") id: Int): Observable<BaseResponse<ArticleModel>>
}
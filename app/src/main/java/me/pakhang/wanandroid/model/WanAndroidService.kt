package me.pakhang.wanandroid.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidService {
    @GET("article/list/{page}/json")
    fun listArticles(@Path("page") page: Int): Call<ArticleModel>
}
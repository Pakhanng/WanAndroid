package me.pakhang.wanandroid.model

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteData {
    private val BASE_URL = "https://www.wanandroid.com"

    fun getArticleList(callback: Callback<ArticleModel>) {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val wanAndroidService = retrofit.create(WanAndroidService::class.java)
        val call = wanAndroidService.listArticles(0)
        call.enqueue(callback)

    }
}
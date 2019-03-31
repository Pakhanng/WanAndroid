package me.pakhang.wanandroid.repository

import me.pakhang.wanandroid.model.WanAndroidApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteData {
    private val BASE_URL = "https://www.wanandroid.com"

    fun loadArticles(page: Int): Call<WanAndroidApi.ArticleResponse> {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(WanAndroidApi::class.java)
        return api.loadArticles(page)

    }
}
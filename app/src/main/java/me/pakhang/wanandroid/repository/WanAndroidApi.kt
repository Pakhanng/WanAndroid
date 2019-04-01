package me.pakhang.wanandroid.repository

import me.pakhang.wanandroid.ui.home.Article
import me.pakhang.wanandroid.ui.home.BannerItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidApi {
    /**
     * 首页文章列表
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    fun loadArticles(@Path("page") page: Int): Call<ArticleResponse>

    class ArticleResponse {
        val data: Data? = null

        inner class Data {
            val datas: List<Article>? = null
        }
    }

    /**
     * 首页文章列表
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("banner/json")
    fun loadbanner(): Call<BannerResponse>

    class BannerResponse {
        val data: List<BannerItem>? = null
    }

}
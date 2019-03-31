package me.pakhang.wanandroid.model

import androidx.paging.PagedList
import me.pakhang.wanandroid.ui.home.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidApi {
    @GET("article/list/{page}/json")
    fun loadArticles(
        @Path("page") page: Int
    ): Call<ArticleResponse>

    class ArticleResponse {
        val data: Data? = null

        inner class Data {
            val datas: PagedList<Article>? = null
        }
    }

}
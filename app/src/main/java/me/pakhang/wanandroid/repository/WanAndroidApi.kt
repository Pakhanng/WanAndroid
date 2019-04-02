package me.pakhang.wanandroid.repository

import android.util.Log
import me.pakhang.wanandroid.model.Article
import me.pakhang.wanandroid.model.BannerItem
import me.pakhang.wanandroid.model.Project
import me.pakhang.wanandroid.model.ProjectCategory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidApi {

    companion object {
        private const val BASE_URL = "https://www.wanandroid.com"
        fun create(): WanAndroidApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanAndroidApi::class.java)
        }
    }

    /**
     * 首页文章列表
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Call<ArticleResponse>

    class ArticleResponse {
        val data: Data? = null

        inner class Data {
            val datas: List<Article>? = null
        }
    }

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBanner(): Call<BannerResponse>

    class BannerResponse {
        val data: List<BannerItem>? = null
    }

    /**
     * 项目分类
     */
    @GET("project/tree/json")
    fun getProjectCategory(): Call<ProjectCategoryResponse>

    class ProjectCategoryResponse {
        val data: List<ProjectCategory>? = null
    }

    /**
     * 某一个分类下项目列表数据，分页展示
     * 参数：
     * cid 分类的id，上面项目分类接口
     * page：拼接在链接中，从1开始。
     */
    @GET("project/list/{page}/json")
    fun getProjects(@Path("page") page: Int, @Query("cid") cid: Int): Call<ProjectResponse>

    class ProjectResponse {
        val data: Data? = null

        inner class Data {
            val datas: List<Project>? = null
        }
    }

}
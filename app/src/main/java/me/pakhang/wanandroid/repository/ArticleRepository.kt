package me.pakhang.wanandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.pakhang.wanandroid.paging.ArticleDataSourceFactory
import me.pakhang.wanandroid.ui.home.Article
import me.pakhang.wanandroid.ui.home.BannerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 使用 Dagger2 更优
object ArticleRepository {
    private var mApi: WanAndroidApi

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://www.wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mApi = retrofit.create(WanAndroidApi::class.java)
    }

    fun loadArticles(): LiveData<PagedList<Article>> {
        val sourceFactory = ArticleDataSourceFactory(mApi)
        val pagedListConfig = PagedList.Config.Builder()
                .setPageSize(2) // 分2页加载，滑到最后才会调用loadAfter
                .setPrefetchDistance(3) // 设置距离最后还有多少个item时调用loadAfter
                .setEnablePlaceholders(false) // 表示是否设置null占位符；
                .build()
        val data = LivePagedListBuilder(sourceFactory, pagedListConfig).build()
        Log.d("cbh", "data = $data")
        return data
    }

    fun loadBanner(): LiveData<List<BannerItem>> {
        val data = MutableLiveData<List<BannerItem>>()
        mApi.loadbanner().enqueue(object : Callback<WanAndroidApi.BannerResponse> {
            override fun onFailure(call: Call<WanAndroidApi.BannerResponse>, t: Throwable) {
                Log.e("cbh", t.localizedMessage)
            }

            override fun onResponse(call: Call<WanAndroidApi.BannerResponse>, response: Response<WanAndroidApi.BannerResponse>) {
                data.postValue(response.body()!!.data)
            }

        })
        return data
    }
}
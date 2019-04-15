package me.pakhang.wanandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.pakhang.wanandroid.model.Article
import me.pakhang.wanandroid.model.Knowledge
import me.pakhang.wanandroid.paging.KnowledgeArticleDataSourceFactory
import me.pakhang.wanandroid.web.WanApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 使用 Dagger2 更优
object KnowledgeRepository {
    private var mApi: WanApi = WanApi.create()

    fun getKnowledge(): LiveData<List<Knowledge>> {
        val data = MutableLiveData<List<Knowledge>>()
        mApi.getKnowledge().enqueue(object : Callback<WanApi.KnowledgeResponse> {
            override fun onFailure(call: Call<WanApi.KnowledgeResponse>, t: Throwable) {
                Log.e("cbh", t.localizedMessage)
            }

            override fun onResponse(call: Call<WanApi.KnowledgeResponse>, response: Response<WanApi.KnowledgeResponse>) {
                data.postValue(response.body()!!.data)
            }

        })
        return data
    }

    fun getArticles(cid: Int): LiveData<PagedList<Article>> {
        val sourceFactory = KnowledgeArticleDataSourceFactory(mApi, cid)
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(2) // 分2页加载，滑到最后才会调用loadAfter
            .setPrefetchDistance(3) // 设置距离最后还有多少个item时调用loadAfter
            .setEnablePlaceholders(false) // 表示是否设置null占位符；
            .build()
        val data = LivePagedListBuilder(sourceFactory, pagedListConfig).build()
        Log.d("cbh", "data = $data")
        return data
    }
}
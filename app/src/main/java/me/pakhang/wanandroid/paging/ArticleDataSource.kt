package me.pakhang.wanandroid.paging

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import me.pakhang.wanandroid.repository.WanApi
import me.pakhang.wanandroid.model.Article

class ArticleDataSource(private val api: WanApi) : ItemKeyedDataSource<Int, Article>() {

    private var page: Int = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Article>) {
        Log.d("cbh", "loadInitial, params.requestedInitialKey=${params.requestedInitialKey}, params.requestedLoadSize=${params.requestedLoadSize}")
        val response = api.getArticles(page).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Article>) {
        Log.d("cbh", "loadAfter, params=$params")
        val response = api.getArticles(++page).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Article>) {
        Log.d("cbh", "loadBefore, params.key=${params.key}, params.requestedLoadSize=${params.requestedLoadSize}")
    }

//    override fun getKey(item: Article) = page

    override fun getKey(item: Article): Int {
        Log.d("cbh", "getKey, item=$item")
        return item.id
    }

}
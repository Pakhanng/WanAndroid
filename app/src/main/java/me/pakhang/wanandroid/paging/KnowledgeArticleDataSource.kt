package me.pakhang.wanandroid.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import me.pakhang.wanandroid.web.WanApi
import me.pakhang.wanandroid.model.Article

class KnowledgeArticleDataSource(private val api: WanApi, private val cid: Int) :
    ItemKeyedDataSource<Int, Article>() {

    private var page: Int = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Article>
    ) {
        val response = api.getKnowledgeArticle(page, cid).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Article>) {
        val response = api.getKnowledgeArticle(++page, cid).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Article>) {
    }

    override fun getKey(item: Article): Int {
        Log.d("cbh", "getKey, item=$item")
        return item.id
    }

}

class KnowledgeArticleDataSourceFactory(private val api: WanApi, private val cid: Int) :
    DataSource.Factory<Int, Article>() {

    private val articles = MutableLiveData<KnowledgeArticleDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = KnowledgeArticleDataSource(api, cid)
        articles.postValue(source)
        return source
    }
}
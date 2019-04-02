package me.pakhang.wanandroid.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import me.pakhang.wanandroid.repository.WanAndroidApi
import me.pakhang.wanandroid.model.Article

class ArticleDataSourceFactory(private val api: WanAndroidApi) : DataSource.Factory<Int, Article>() {

    private val articles = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = ArticleDataSource(api)
        articles.postValue(source)
        return source
    }
}
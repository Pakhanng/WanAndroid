package me.pakhang.wanandroid.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import me.pakhang.wanandroid.web.WanApi
import me.pakhang.wanandroid.model.Project

class ProjectDataSource(private val api: WanApi, private val cid: Int) :
    ItemKeyedDataSource<Int, Project>() {

    private var page: Int = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Project>
    ) {
        Log.d(
            "cbh",
            "loadInitial, params.requestedInitialKey=${params.requestedInitialKey}, params.requestedLoadSize=${params.requestedLoadSize}"
        )
        val response = api.getProjects(page, cid).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Project>) {
        Log.d("cbh", "loadAfter, params=$params")
        val response = api.getProjects(++page, cid).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Project>) {
        Log.d(
            "cbh",
            "loadBefore, params.key=${params.key}, params.requestedLoadSize=${params.requestedLoadSize}"
        )
    }

//    override fun getKey(item: Article) = page

    override fun getKey(item: Project): Int {
        return item.id
    }

}

class ProjectDataSourceFactory(private val api: WanApi, private val cid: Int) :
    DataSource.Factory<Int, Project>() {

    private val projects = MutableLiveData<ProjectDataSource>()

    override fun create(): DataSource<Int, Project> {
        val source = ProjectDataSource(api, cid)
        projects.postValue(source)
        return source
    }
}

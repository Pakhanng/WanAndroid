package me.pakhang.wanandroid.paging

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import me.pakhang.wanandroid.repository.WanAndroidApi
import me.pakhang.wanandroid.model.Project

class ProjectDataSource(private val api: WanAndroidApi, private val cid: Int) : ItemKeyedDataSource<Int, Project>() {

    private var page: Int = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Project>) {
        Log.d("cbh", "loadInitial, params.requestedInitialKey=${params.requestedInitialKey}, params.requestedLoadSize=${params.requestedLoadSize}")
        val response = api.getProjects(page, cid).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Project>) {
        Log.d("cbh", "loadAfter, params=$params")
        val response = api.getProjects(++page, cid).execute()
        callback.onResult(response.body()!!.data!!.datas!!)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Project>) {
        Log.d("cbh", "loadBefore, params.key=${params.key}, params.requestedLoadSize=${params.requestedLoadSize}")
    }

//    override fun getKey(item: Article) = page

    override fun getKey(item: Project): Int {
        return item.id
    }

}
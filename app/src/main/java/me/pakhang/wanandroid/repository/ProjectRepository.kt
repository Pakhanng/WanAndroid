package me.pakhang.wanandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.pakhang.wanandroid.paging.ProjectDataSourceFactory
import me.pakhang.wanandroid.model.Project
import me.pakhang.wanandroid.model.ProjectCategory
import me.pakhang.wanandroid.web.WanApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 使用 Dagger2 更优
object ProjectRepository {
    private var mApi: WanApi = WanApi.create()

    fun getProjectCategory(): LiveData<List<ProjectCategory>> {
        val data = MutableLiveData<List<ProjectCategory>>()
        mApi.getProjectCategory().enqueue(object : Callback<WanApi.ProjectCategoryResponse> {
            override fun onFailure(call: Call<WanApi.ProjectCategoryResponse>, t: Throwable) {
                Log.e("cbh", t.localizedMessage)
            }

            override fun onResponse(call: Call<WanApi.ProjectCategoryResponse>, response: Response<WanApi.ProjectCategoryResponse>) {
                data.postValue(response.body()!!.data)
            }

        })
        return data
    }

    fun getProjects(cid: Int): LiveData<PagedList<Project>> {
        val sourceFactory = ProjectDataSourceFactory(mApi, cid)
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
package me.pakhang.wanandroid.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import me.pakhang.wanandroid.repository.WanAndroidApi
import me.pakhang.wanandroid.model.Project

class ProjectDataSourceFactory(private val api: WanAndroidApi, private val cid: Int) : DataSource.Factory<Int, Project>() {

    private val projects = MutableLiveData<ProjectDataSource>()

    override fun create(): DataSource<Int, Project> {
        val source = ProjectDataSource(api, cid)
        projects.postValue(source)
        return source
    }
}
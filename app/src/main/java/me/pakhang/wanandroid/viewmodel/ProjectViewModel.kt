package me.pakhang.wanandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.pakhang.wanandroid.model.Project
import me.pakhang.wanandroid.repository.ProjectRepository

class ProjectViewModel(private val repo: ProjectRepository) : ViewModel() {
    val projectCategory = repo.getProjectCategory()
    private lateinit var projects: LiveData<PagedList<Project>>

    fun getProjects(cid: Int): LiveData<PagedList<Project>> {
        projects = repo.getProjects(cid)
        return projects
    }
}

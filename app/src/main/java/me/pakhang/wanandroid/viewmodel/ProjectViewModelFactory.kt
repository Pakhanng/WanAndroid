package me.pakhang.wanandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.pakhang.wanandroid.repository.ProjectRepository

@Suppress("UNCHECKED_CAST")
class ProjectViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ProjectViewModel(ProjectRepository) as T
}
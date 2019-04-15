package me.pakhang.wanandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.pakhang.wanandroid.repository.ArticleRepository

class HomeViewModel(repo: ArticleRepository) : ViewModel() {
    val banner = repo.getBanner()
    val articles = repo.getArticles()
}

/**
 * 因为 ViewModel 需要 Repository 对象，Fragment 不能直接构建 ViewModel，而是传入 Factory 作为 ViewModelProviders.of() 的参数
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        HomeViewModel(ArticleRepository) as T
}
package me.pakhang.wanandroid.viewmodel

import androidx.lifecycle.ViewModel
import me.pakhang.wanandroid.repository.ArticleRepository

class HomeViewModel(repo: ArticleRepository) : ViewModel() {
    val banner = repo.getBanner()
    val articles = repo.getArticles()
}

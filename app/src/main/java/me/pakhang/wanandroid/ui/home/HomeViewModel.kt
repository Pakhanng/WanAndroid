package me.pakhang.wanandroid.ui.home

import androidx.lifecycle.ViewModel
import me.pakhang.wanandroid.repository.ArticleRepository

class HomeViewModel(repo: ArticleRepository) : ViewModel() {
    var banner = repo.loadBanner()
    var articles = repo.loadArticles()
}


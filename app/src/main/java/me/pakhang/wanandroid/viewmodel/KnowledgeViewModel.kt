package me.pakhang.wanandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import me.pakhang.wanandroid.model.Article
import me.pakhang.wanandroid.repository.KnowledgeRepository

class KnowledgeViewModel(val repo: KnowledgeRepository) : ViewModel() {

    val knowledge = repo.getKnowledge()

    fun getArticles(cid: Int): LiveData<PagedList<Article>> = repo.getArticles(cid)

}

@Suppress("UNCHECKED_CAST")
class KnowledgeViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        KnowledgeViewModel(KnowledgeRepository) as T
}

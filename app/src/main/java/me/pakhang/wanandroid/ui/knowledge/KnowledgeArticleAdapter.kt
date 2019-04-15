package me.pakhang.wanandroid.ui.knowledge

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import me.pakhang.wanandroid.databinding.ItemListArticleBinding
import me.pakhang.wanandroid.model.Article
import me.pakhang.wanandroid.ui.home.ArticleViewHolder

class KnowledgeArticleAdapter :
    PagedListAdapter<Article, ArticleViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        ItemListArticleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d("cbh", "areItemsTheSame: oldItem = $oldItem, newItem = $newItem")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d("cbh", "areContentsTheSame: oldItem = $oldItem, newItem = $newItem")
            return oldItem == newItem
        }
    }

}

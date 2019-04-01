package me.pakhang.wanandroid.ui.home

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.databinding.ListArticleBinding

class ArticleViewHolder(private val binding: ListArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.clickListener = View.OnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToArticleDetailFragment(article.link)
                it.findNavController().navigate(direction)
            }
        }
}

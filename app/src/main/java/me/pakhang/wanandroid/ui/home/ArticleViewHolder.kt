package me.pakhang.wanandroid.ui.home

import android.view.View
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.databinding.ItemListArticleBinding
import me.pakhang.wanandroid.model.Article

class ArticleViewHolder(private val binding: ItemListArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.clickListener = View.OnClickListener {
                val direction = HomeFragmentDirections.actionHomeToWebView(article.link)
                it.findNavController().navigate(direction)
            }
        }
}

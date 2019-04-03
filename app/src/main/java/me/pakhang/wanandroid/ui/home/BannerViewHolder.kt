package me.pakhang.wanandroid.ui.home

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import me.pakhang.wanandroid.databinding.ItemListBannerBinding
import me.pakhang.wanandroid.model.BannerItem

class BannerViewHolder(private val binding: ItemListBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(bannerItems: List<BannerItem>) {
        Log.d("cbh", "bannerItems=$bannerItems")
        val images = ArrayList<String>()
        val titles = ArrayList<String>()
        for (index in 0 until bannerItems.size) {
            images.add(bannerItems[index].imagePath)
            titles.add(bannerItems[index].title)
        }
        val banner = binding.banner
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        banner.setImageLoader(GlideImageLoader())
        banner.isAutoPlay(true)
        banner.setDelayTime(3000)
        banner.setImages(images)
        banner.setBannerTitles(titles)
        banner.start()

        banner.setOnBannerListener { position ->
            val direction =
                HomeFragmentDirections.actionHomeFragmentToWebViewFragment(bannerItems[position].url)
            banner.findNavController().navigate(direction)
        }
    }

    inner class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context!!).load(path).into(imageView!!)
        }
    }
}

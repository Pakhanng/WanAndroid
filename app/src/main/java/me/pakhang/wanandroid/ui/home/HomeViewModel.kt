package me.pakhang.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.pakhang.wanandroid.model.WanAndroidApi
import me.pakhang.wanandroid.repository.RemoteData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    //    val articles = MutableLiveData<List<Article>>()
    val articles = MutableLiveData<PagedList<Article>>()


    fun loadArticles() {
        RemoteData().loadArticles(0).enqueue(object : Callback<WanAndroidApi.ArticleResponse> {
            override fun onFailure(call: Call<WanAndroidApi.ArticleResponse>, t: Throwable) {
                Log.e("cbh", t.toString())
            }

            override fun onResponse(
                call: Call<WanAndroidApi.ArticleResponse>,
                response: Response<WanAndroidApi.ArticleResponse>
            ) {
                articles.value = response.body()!!.data!!.datas
            }

        })
    }
}

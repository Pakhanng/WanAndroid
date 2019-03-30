package me.pakhang.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.pakhang.wanandroid.model.ArticleModel
import me.pakhang.wanandroid.model.RemoteData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    val articles = MutableLiveData<List<Article>>()


    fun fetchArticles() {

        RemoteData().getArticleList(object : Callback<ArticleModel> {
            override fun onFailure(call: Call<ArticleModel>, t: Throwable) {
                Log.e("cbh", t.toString())
            }

            override fun onResponse(call: Call<ArticleModel>, response: Response<ArticleModel>) {
                articles.value = response.body()!!.data!!.datas
            }

        })
    }
}

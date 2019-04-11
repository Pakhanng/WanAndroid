package me.pakhang.wanandroid.repository

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 使用 Dagger2 更优
object UserRepository {
    private var mApi: WanApi = WanApi.create()

    fun signIn(userName: String?, password: String?) {
        mApi.login(userName, password).enqueue(object : Callback<WanApi.LoginResponse> {
            override fun onFailure(call: Call<WanApi.LoginResponse>, t: Throwable) {
                Log.e("cbh", t.localizedMessage)
            }

            override fun onResponse(call: Call<WanApi.LoginResponse>, response: Response<WanApi.LoginResponse>) {
                Log.d("cbh", "onResponse = ${response.body()!!.data!!.username}")
            }

        })
    }

    fun signUp() {

    }

}
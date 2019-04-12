package me.pakhang.wanandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.pakhang.wanandroid.model.Resource
import me.pakhang.wanandroid.model.User
import me.pakhang.wanandroid.web.WanApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 使用 Dagger2 更优
object UserRepository {
    private var mApi: WanApi = WanApi.create()

    fun signIn(userName: String, password: String): MutableLiveData<Resource<User>> {

        val data = MutableLiveData<Resource<User>>()
        data.value = Resource.loading(null)

        mApi.login(userName, password).enqueue(object : Callback<WanApi.LoginResponse> {
            override fun onFailure(call: Call<WanApi.LoginResponse>, t: Throwable) {
                Log.e("cbh", "onFailure: ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<WanApi.LoginResponse>, response: Response<WanApi.LoginResponse>) {
                val body = response.body()
                if (body == null) {
                    data.value = Resource.error("unknown error", null)
                    return
                }
                if (body.errorCode != 0) {
                    data.value = Resource.error(body.errorMsg, null)
                    return
                }
                data.value = Resource.success(body.data)
                Log.d("cbh", "onResponse: ${data.value}")
            }

        })
        return data
    }

    fun signUp(userName: String, password: String, confirmPassword: String) {

    }

}
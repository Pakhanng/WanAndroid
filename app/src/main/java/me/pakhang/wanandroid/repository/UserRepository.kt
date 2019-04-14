package me.pakhang.wanandroid.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import me.pakhang.wanandroid.App
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
                Log.e("cbh", "signIn onFailure: ${t.localizedMessage}")
                data.value = Resource.error("unknown error", null)
            }

            override fun onResponse(
                call: Call<WanApi.LoginResponse>,
                response: Response<WanApi.LoginResponse>
            ) {
                val body = response.body()
                if (body == null) {
                    data.value = Resource.error("unknown error", null)
                    return
                }
                if (body.errorCode != 0) {
                    data.value = Resource.error(body.errorMsg, null)
                    return
                }
                val user = body.data
                data.value = Resource.success(user)
                App.signIn(user)
                Log.d("cbh", "onResponse: $user")
            }

        })
        return data
    }

    fun signOut() {
        mApi.logout().enqueue(object : Callback<WanApi.BaseResponse> {
            override fun onFailure(call: Call<WanApi.BaseResponse>, t: Throwable) {
                Log.e("cbh", "logout onFailure: ${t.localizedMessage}")

            }

            override fun onResponse(
                call: Call<WanApi.BaseResponse>,
                response: Response<WanApi.BaseResponse>
            ) {
            }

        })
        App.signOut()
    }

    fun signUp(
        userName: String, password: String, confirmPassword: String
    ): MutableLiveData<Resource<User>> {
        val data = MutableLiveData<Resource<User>>()
        data.value = Resource.loading(null)

        mApi.register(userName, password, confirmPassword)
            .enqueue(object : Callback<WanApi.RegisterResponse> {
                override fun onFailure(call: Call<WanApi.RegisterResponse>, t: Throwable) {
                    Log.e("cbh", "signUp onFailure: ${t.localizedMessage}")
                    data.value = Resource.error("unknown error", null)
                }

                override fun onResponse(
                    call: Call<WanApi.RegisterResponse>, response: Response<WanApi.RegisterResponse>
                ) {
                    val body = response.body()
                    if (body == null) {
                        data.value = Resource.error("unknown error", null)
                        return
                    }
                    if (body.errorCode != 0) {
                        data.value = Resource.error(body.errorMsg, null)
                        return
                    }
                    val user = body.data
                    data.value = Resource.success(user)
                    App.signIn(user)
                    Log.d("cbh", "signUp onResponse: $user")
                }

            })
        return data
    }

}
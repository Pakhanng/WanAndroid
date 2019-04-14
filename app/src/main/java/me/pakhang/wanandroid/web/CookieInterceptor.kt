package me.pakhang.wanandroid.web

import me.pakhang.wanandroid.util.saveCookie
import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cookies = response.headers("Set-Cookie")
        if (cookies.isNotEmpty()) {
            saveCookie(cookies)
        }

        return response
    }
}
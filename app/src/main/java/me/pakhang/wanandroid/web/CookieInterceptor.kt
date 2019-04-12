package me.pakhang.wanandroid.web

import android.preference.PreferenceManager
import me.pakhang.wanandroid.App
import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val setCookieHeaders = response.headers("Set-Cookie")
        if (setCookieHeaders.isNotEmpty()) {
            PreferenceManager
                .getDefaultSharedPreferences(App.CONTEXT)
                .edit()
                .putStringSet("cookies", setCookieHeaders.toSet())
                .apply()
        }

        return response
    }
}
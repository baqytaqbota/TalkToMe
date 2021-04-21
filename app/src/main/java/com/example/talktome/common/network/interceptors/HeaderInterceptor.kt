package com.example.talktome.common.network.interceptors

import android.content.Context
import android.content.Intent
import com.example.talktome.common.pref.Preference
import com.example.talktome.ui.unauthorized.ui.UnauthorizedActivity
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.net.HttpURLConnection


class HeaderInterceptor : Interceptor, KoinComponent {

    private val pref by inject<Preference>()
    private val context by inject<Context>()

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val request = builder
            .header("Content-Type", "application/json")
            .header("Accept", "*/*")
            .header("AccessToken", "${pref.getToken()}")
            .build()

        print(".....................////////////////////////////${pref.getToken()}")

        val response = chain.proceed(request)

        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            pref.clearData()
            val intent = Intent(context, UnauthorizedActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
        return response
    }
}

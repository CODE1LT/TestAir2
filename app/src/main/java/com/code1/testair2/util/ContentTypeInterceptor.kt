package com.code1.testair2.util

import okhttp3.Interceptor
import okhttp3.Response

private const val KEY_CONTENT_TYPE_WEATHER_SERVICE = "Content-Type"
private const val CONTENT_TYPE_WEATHER_SERVICE = "application/json"

class ContentTypeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(
                    KEY_CONTENT_TYPE_WEATHER_SERVICE,
                    CONTENT_TYPE_WEATHER_SERVICE
                )
                .build()
        )
    }
}
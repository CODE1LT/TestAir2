package com.code1.testair2.injection.remotemodules

import android.content.Context
import com.code1.testair2.BuildConfig
import com.code1.testair2.common.BuildTypes
import com.code1.testair2.common.ThreeTenTypeAdapters
import com.code1.testair2.util.ContentTypeInterceptor
import com.code1.testair2.util.get
import com.code1.testair2.util.network.connectivity.NetworkConnectionInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val KEY_CONTENT_TYPE = "KEY_CONTENT_TYPE"
const val KEY_NETWORKING_MODULE = "KEY_NETWORKING_MODULE"

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named(KEY_NETWORKING_MODULE)
    fun provideClient(
        @Named(KEY_CONTENT_TYPE) contentTypeInterceptor: Interceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(contentTypeInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor(getLoggingInterceptor())
            .build()

    private fun getLoggingInterceptor() =
        if (BuildConfig.BUILD_TYPE == BuildTypes.MOCK.value || BuildConfig.BUILD_TYPE == BuildTypes.DEBUG.value) {
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        } else {
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }
        }

    @Singleton
    @Provides
    @Named(KEY_NETWORKING_MODULE)
    fun provideRetrofit(@Named(KEY_NETWORKING_MODULE) client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(get<EndpointBase>())
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .serializeNulls()
                        .registerTypeAdapter(
                            Instant::class.java,
                            ThreeTenTypeAdapters.create(
                                Instant::class.java, DateTimeFormatter.ISO_INSTANT
                            )
                        )
                        .registerTypeAdapter(
                            LocalDateTime::class.java,
                            ThreeTenTypeAdapters.create(
                                LocalDateTime::class.java, DateTimeFormatter.ISO_DATE_TIME
                            )
                        )
                        .registerTypeAdapter(
                            OffsetDateTime::class.java,
                            ThreeTenTypeAdapters.create(
                                OffsetDateTime::class.java, DateTimeFormatter.ISO_OFFSET_DATE_TIME
                            )
                        )
                        .create()
                )
            )
            .build()

    @Singleton
    @Provides
    @Named(KEY_CONTENT_TYPE)
    fun provideContentTypeInterceptor(): Interceptor =
        ContentTypeInterceptor()

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(context: Context): NetworkConnectionInterceptor =
        NetworkConnectionInterceptor(context)
}
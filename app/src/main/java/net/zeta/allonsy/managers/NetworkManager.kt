package net.zeta.allonsy.managers

import net.zeta.allonsy.utils.Constants
import net.zeta.allonsy.services.AllonsyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object NetworkManager{
    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.i(it)
        }).setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val okHttpClient: OkHttpClient by lazy(
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)::build
    )
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.endpoint)
            .client(okHttpClient)

            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    val allonsyService : AllonsyService by lazy {
        retrofit.create(AllonsyService::class.java)
    }
}
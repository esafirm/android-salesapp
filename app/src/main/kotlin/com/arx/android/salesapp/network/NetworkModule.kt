package com.arx.android.salesapp.network

import android.util.Log
import com.arx.android.salesapp.dagger.ApplicationScope
import com.arx.android.salesapp.data.constant.URLs
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.incendiary.androidcommon.etc.Logger
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module class NetworkModule {

  @ApplicationScope @Provides fun provideGson(): Gson {
    return Gson()
  }

  @ApplicationScope @Provides fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URLs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()
  }

  @ApplicationScope @Provides fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor { message -> Logger.log(Log.DEBUG, message) }
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor { chain ->

          val request = chain.request()
          val httpUrl = request.url()
              .newBuilder()
              .build()

          val newRequest = request.newBuilder()
		          .url(httpUrl)
		          .addHeader("Key", "9572ed3dd320fd94a3a98ce2e56e70a2b3bd7947")
		          .build();

          chain.proceed(newRequest)
        }
        .addInterceptor(loggingInterceptor)
        .build()
  }

  @ApplicationScope @Provides fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }
}
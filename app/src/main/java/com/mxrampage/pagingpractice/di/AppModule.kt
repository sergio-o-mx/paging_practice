package com.mxrampage.pagingpractice.di

import com.mxrampage.pagingpractice.BuildConfig
import com.mxrampage.pagingpractice.network.APIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Suppress("SpellCheckingInspection")
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {chain ->
            val url = chain
                .request()
                .url()
                .newBuilder()
                .addQueryParameter("client_id", BuildConfig.ACCESS_KEY)
                .build()
            val request = chain
                .request()
                .newBuilder()
                .url(url)
            return@addInterceptor chain.proceed(request.build())
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)
}

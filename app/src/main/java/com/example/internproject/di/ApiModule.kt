package com.example.internproject.di

import com.example.internproject.BuildConfig
import com.example.internproject.api.ApiService
import com.example.internproject.repository.ApiRepository
import com.example.internproject.response.MovieListResponse
import com.example.internproject.utils.Constants.API_KEY
import com.example.internproject.utils.Constants.BASE_URL
import com.example.internproject.utils.Constants.ConnectionTimeOut
import com.example.internproject.utils.OnItemClick
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

//    @Provides
//    @Singleton
//    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun connectionTimeOut() = ConnectionTimeOut


    //    @Provides
//    @Singleton
//    fun provideOKHttpClient():ApiService  {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val requestInterceptor = Interceptor { chain ->
//            val url = chain.request()
//                .url
//                .newBuilder()
//                .addQueryParameter("api_key", API_KEY)
//                .build()
//            val request = chain.request().newBuilder()
//                .url(url)
//                .build()
//
//            return@Interceptor chain.proceed(request)
//        }
//
//        OkHttpClient
//            .Builder()
//            .addInterceptor(requestInterceptor)
//            .addInterceptor(loggingInterceptor)
//    }
//
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()


    //
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
//
//
//    @Provides
//    @Singleton
//    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
//        OkHttpClient
//            .Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun repo(apiService: ApiService): ApiRepository = ApiRepository(apiService)

}
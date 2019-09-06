package com.example.employeesmanagermentapplication

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiInstance {
    companion object {
        var BASE_URL = "http://dummy.restapiexample.com/"
        fun getRetrofit(): Retrofit{
            var gson = GsonBuilder().setLenient().create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit =
                Retrofit.Builder().client(client).baseUrl("http://dummy.restapiexample.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit
        }
    }
}
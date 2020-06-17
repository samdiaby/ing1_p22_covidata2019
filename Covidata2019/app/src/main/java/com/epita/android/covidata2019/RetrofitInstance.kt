package com.epita.android.covidata2019

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val baseURL : String = "https://api.covid19api.com/"
    private var retrofit : Retrofit = Retrofit.Builder()
                                        .baseUrl(baseURL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

    fun getRetrofitInstance() : Retrofit {
        return retrofit
    }
}
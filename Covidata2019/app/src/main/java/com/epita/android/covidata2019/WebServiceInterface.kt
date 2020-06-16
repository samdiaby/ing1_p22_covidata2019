package com.epita.android.covidata2019

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path
import retrofit2.http.Query;

interface WebServiceInterface {
    @GET("countries")
    fun listToDos(@Query("Country") Country: String): Call<List<Countries>>

}

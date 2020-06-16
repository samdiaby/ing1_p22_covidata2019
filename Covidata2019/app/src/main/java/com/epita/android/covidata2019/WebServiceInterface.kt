package com.epita.android.covidata2019

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path
import retrofit2.http.Query;

interface WebServiceInterface {
    @GET("countries")
    fun GetCountries(): Call<List<Countries>>

    @GET("countries")
    fun GetWorldData(@Query("Country") Country : String): Call<WorldData>

    @GET("all")
    fun GetDatasFrom(@Query("Country") Country : String) : Call<List<AllDatas>>
}

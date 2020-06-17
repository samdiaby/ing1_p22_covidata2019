package com.epita.android.covidata2019

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart
import retrofit2.http.Path
import retrofit2.http.Query;

interface WebServiceInterface {
    @GET("countries")
    fun GetCountries(): Call<List<Countries>>

    @GET("summary")
    fun GetWorldData(): Call<gdata>

    @GET("country/{ctry}/status/confirmed")
    fun GetDatasFromConfs(@Path("ctry") cntry : String,
                     @Query("from") from : String,
                    @Query("to") to : String) : Call<List<AllDatas>>

    @GET("country/{ctry}/status/deaths")
    fun GetDatasFromDeaths(@Path("ctry") cntry : String,
                          @Query("from") from : String,
                          @Query("to") to : String) : Call<List<AllDatas>>

    @GET("country/{ctry}/status/recovered")
    fun GetDatasFromRecov(@Path("ctry") cntry : String,
                           @Query("from") from : String,
                           @Query("to") to : String) : Call<List<AllDatas>>
}

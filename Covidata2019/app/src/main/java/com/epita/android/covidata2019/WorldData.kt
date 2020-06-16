package com.epita.android.covidata2019

data class WorldData(val Country : String, val CountryCode : String, val Slug : String,
    val NewConfirmed : String, val TotalConfired  : Int, val NewDeaths : Int, val TotalDeaths : Int,
    val NewRecovered : Int, val TotalRecovered : Int, val Date : String)


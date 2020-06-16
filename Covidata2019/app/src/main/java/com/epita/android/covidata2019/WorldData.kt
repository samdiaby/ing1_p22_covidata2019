package com.epita.android.covidata2019

data class WorldData(val Country : String, val CountryCode : String, val Slug : String,
    val NewConfirmed : String, val TotalConfirmed  : Int, val NewDeaths : Int, val TotalDeaths : Int,
    val NewRecovered : Int, val TotalRecovered : Int, val Date : String)

data class global( val NewConfirmed : String, val TotalConfirmed  : Int, val NewDeaths : Int, val TotalDeaths : Int,
                   val NewRecovered : Int, val TotalRecovered : Int)
data class gdata(val Global: global, val Countries:List<WorldData>, val Date : String)
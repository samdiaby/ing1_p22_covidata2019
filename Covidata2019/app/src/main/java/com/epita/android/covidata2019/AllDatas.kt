package com.epita.android.covidata2019

data class AllDatas(val Country : String, val CountryCode : String, val Lat : String, val Lon : String,
        val Confirmed : Int, val Deaths : Int, val Recovered : Int, val Active : Int, val Date : String,
        val LocationID : String)
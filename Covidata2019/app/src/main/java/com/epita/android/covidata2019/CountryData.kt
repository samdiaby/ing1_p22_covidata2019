package com.epita.android.covidata2019

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_country_data.*

class CountryData : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_data)

        // get the country name from the pressed button
        val originIntent = intent
        val countryName = originIntent.getStringExtra("countryName")

        // set the name of the country selected from the list
        activity_country_data_country_text.text = countryName

        // TODO : set the country's data from the API
    }
}
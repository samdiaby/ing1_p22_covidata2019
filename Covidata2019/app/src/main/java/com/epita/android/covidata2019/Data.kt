package com.epita.android.covidata2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_data.*

class Data : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val data : MutableList<Countries> = arrayListOf()

        // changer pour récupérer les données du web service
        data.add(Countries("France"))
        data.add(Countries("United States"))
        data.add(Countries("Japan"))
        data.add(Countries("China"))
        data.add(Countries("Belgium"))
        data.add(Countries("Canada"))

        // set the adapter in order to set the recycler view (in activity_data)
        activity_data_list_countries.adapter = CountriesAdapter(this@Data, data)

        // change this for webservice uses ?
        activity_data_list_countries.setHasFixedSize(true)

        //recycler view appearance
        activity_data_list_countries.layoutManager = LinearLayoutManager(this)
    }
}
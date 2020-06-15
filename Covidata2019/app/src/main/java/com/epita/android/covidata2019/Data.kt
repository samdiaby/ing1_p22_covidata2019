package com.epita.android.covidata2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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

        // create a listener in order to access to the details for each country
        val onClickListener = View.OnClickListener { clickedRowView ->
            val clickedCountry: Countries = data[clickedRowView.tag as Int]
            Toast.makeText(this, "Clicked : " + clickedCountry.name,
                                                                        Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CountryData::class.java)

            val message = clickedCountry.name
            intent.putExtra("countryName", message)

            startActivity(intent);
        }

        // set the adapter in order to set the recycler view (in activity_data)
        activity_data_list_countries.adapter = CountriesAdapter(this, data,
                                                                                    onClickListener)

        // change this for webservice uses ?
        activity_data_list_countries.setHasFixedSize(true)

        //recycler view appearance
        activity_data_list_countries.layoutManager = LinearLayoutManager(this)
    }
}
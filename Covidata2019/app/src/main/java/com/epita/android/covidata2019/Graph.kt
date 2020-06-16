package com.epita.android.covidata2019

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_graph_data.*

class Graph : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_data)

        val data : MutableList<Countries> = arrayListOf()
        val infos : MutableList<GraphInfo> = arrayListOf()
/*
        // changer pour récupérer les données du web service
        data.add(Countries("France"))
        data.add(Countries("United States"))
        data.add(Countries("Japan"))
        data.add(Countries("China"))
        data.add(Countries("Belgium"))
        data.add(Countries("Canada"))*/

        // changer pour récupérer les données du web service
        infos.add(GraphInfo("April 5", 50))
        infos.add(GraphInfo("April 6", 25))
        infos.add(GraphInfo("April 7", 10))
        infos.add(GraphInfo("April 8", 30))
        infos.add(GraphInfo("April 9", 40))
        infos.add(GraphInfo("April 10", 60))
        infos.add(GraphInfo("April 11", 75))
        infos.add(GraphInfo("April 12", 20))
        infos.add(GraphInfo("december 13", 115))


        // create a listener in order to access to the details for each country
        val onClickListener = View.OnClickListener { clickedRowView ->
            val clickedCountry: Countries = data[clickedRowView.tag as Int]
            Toast.makeText(this, "Clicked : " + clickedCountry.Country,
                Toast.LENGTH_SHORT).show()
        }

        // set the adapter in order to set the recycler view (in activity_data)
        activity_graph_data_country_list.adapter = CountriesAdapter(this, data,
            onClickListener)

        // change this for webservice uses ?
        activity_graph_data_country_list.setHasFixedSize(true)

        //recycler view appearance
        activity_graph_data_country_list.layoutManager = LinearLayoutManager(this,
                                            LinearLayoutManager.HORIZONTAL, false)


        activity_graph_data_info_list.adapter = GraphInfoAdapter(this, infos)
        activity_graph_data_info_list.setHasFixedSize(true)
        activity_graph_data_info_list.layoutManager = LinearLayoutManager(this)
    }
}
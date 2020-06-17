package com.epita.android.covidata2019

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_graph_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Graph : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_data)

        val service: WebServiceInterface = RetrofitInstance().getRetrofitInstance()
            .create(WebServiceInterface::class.java)
        var countryName : String = "France"

        
        // handle graphical datas
        val infosCallback: Callback<List<GraphInfo>> = object : Callback<List<GraphInfo>> {
            override fun onFailure(call: Call<List<GraphInfo>>, t: Throwable) {
                // Code here what happens if calling the WebService fails
                Log.w("TAG", "WebService call failed")
                Log.w("TAG", "WebService call failed : ")

            }
            override fun onResponse(call: Call<List<GraphInfo>>, response:
            Response<List<GraphInfo>>
            ) {
                if (response.code() == 200) {
                    // We got our data !
                    val res = response.body()!!
                    if (res != null) {

                        Log.d("TAG", "WebService success : " + res.size)
                    }

                    activity_graph_data_info_list.adapter = GraphInfoAdapter(this@Graph, res)
                    activity_graph_data_info_list.setHasFixedSize(true)
                    activity_graph_data_info_list.layoutManager = LinearLayoutManager(this@Graph)
                }
            }
        }

        val wsCallback: Callback<List<Countries>> = object : Callback<List<Countries>> {
            override fun onFailure(call: Call<List<Countries>>, t: Throwable) {
                // Code here what happens if calling the WebService fails
                Log.w("TAG", "WebService call failed")
            }
            override fun onResponse(call: Call<List<Countries>>, response:
            Response<List<Countries>>
            ) {
                if (response.code() == 200) {
                    // We got our data !
                    val res = response.body()!!
                    if (res != null) {

                        Log.d("TAG", "WebService success : " + res.size)
                    }

                    val onClickListener = View.OnClickListener { clickedRowView ->
                        val clickedCountry: Countries = clickedRowView.tag as Countries
                        countryName = clickedCountry.Slug
                        service.getGraphDatasConf(countryName,
                            "2020-03-01T00:00:00Z", "2020-06-15T00:00:00Z").enqueue(infosCallback)
                    }

                    // set the adapter in order to set the recycler view (in activity_data)
                    activity_graph_data_country_list.adapter = CountriesAdapterGraph(this@Graph, res,
                        onClickListener)

                    // change this for webservice uses ?
                    activity_graph_data_country_list.setHasFixedSize(true)

                    //recycler view appearance
                    activity_graph_data_country_list.layoutManager = LinearLayoutManager(this@Graph,
                                                LinearLayoutManager.HORIZONTAL, false)

                }
            }
        }

        service.GetCountries().enqueue(wsCallback)

        Log.d("TAG", "slug name : " + countryName)
        activity_graph_data_confirmed_btn.setOnClickListener() {
            service.getGraphDatasConf(countryName,
                "2020-03-01T00:00:00Z", "2020-06-15T00:00:00Z").enqueue(infosCallback)
        }

        activity_graph_data_deaths_btn.setOnClickListener {
            service.getGraphDatasDeaths(countryName,
                "2020-03-01T00:00:00Z", "2020-06-15T00:00:00Z").enqueue(infosCallback)
        }

        activity_graph_data_recovered_btn.setOnClickListener {
            service.getGraphDatasRecov(countryName,
                "2020-03-01T00:00:00Z", "2020-06-15T00:00:00Z").enqueue(infosCallback)
        }
    }
}
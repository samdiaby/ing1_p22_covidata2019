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

        // The base URL where the WebService is located
        val baseURL = "https://api.covid19api.com/"
        // Use GSON library to create our JSON parser
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
        // Create a Retrofit client object targeting the provided URL
        // and add a JSON converter (because we are expecting json responses)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()
        // Use the client to create a service:
        // an object implementing the interface to the WebService

        val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)
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
                        val clickedCountry: Countries = res[clickedRowView.tag as Int]
                        Toast.makeText(this@Graph, "Clicked : " + clickedCountry.Country,
                            Toast.LENGTH_SHORT).show()
                    }

                    // set the adapter in order to set the recycler view (in activity_data)
                    activity_graph_data_country_list.adapter = CountriesAdapter(this@Graph, res,
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

        val infos : MutableList<GraphInfo> = arrayListOf()

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


        activity_graph_data_info_list.adapter = GraphInfoAdapter(this, infos)
        activity_graph_data_info_list.setHasFixedSize(true)
        activity_graph_data_info_list.layoutManager = LinearLayoutManager(this)
    }
}
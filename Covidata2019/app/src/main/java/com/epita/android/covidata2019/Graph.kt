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

        activity_graph_data_info_list.adapter = GraphInfoAdapter(this, infos)
        activity_graph_data_info_list.setHasFixedSize(true)
        activity_graph_data_info_list.layoutManager = LinearLayoutManager(this)
    }
}
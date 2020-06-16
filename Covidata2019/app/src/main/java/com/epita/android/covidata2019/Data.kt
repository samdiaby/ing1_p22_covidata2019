package com.epita.android.covidata2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Data : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        // A List to store or objects

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
        var names = arrayListOf<String>()
        var data = arrayListOf<Countries>()

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
                        for (i in res.indices)
                        {
                            names.add(res.get(i).Country)
                        }

                        Log.d("TAG", "WebService success : " + data.size)
                        Log.d("TAG", "WebService names : " + names)

                    }
                    val onClickListener = View.OnClickListener { clickedRowView ->
                        val clickedCountry: Countries = res[clickedRowView.tag as Int]
                        Toast.makeText(this@Data, "Clicked : " + clickedCountry.Country,
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Data, CountryData::class.java)

                        val message = clickedCountry.Country
                        intent.putExtra("countryName", message)

                        startActivity(intent)
                    }

                    // set the adapter in order to set the recycler view (in activity_data)
                    activity_data_list_countries.adapter = CountriesAdapter(this@Data, res,
                        onClickListener)

                    // change this for webservice uses ?
                    //activity_data_list_countries.setHasFixedSize(true)

                    //recycler view appearance
                    activity_data_list_countries.layoutManager = LinearLayoutManager(this@Data)

                }
            }
        }
        service.listToDos("France").enqueue(wsCallback)


        /*val data : MutableList<Countries> = arrayListOf()

        // changer pour récupérer les données du web service
        data.add(Countries("France"))
        data.add(Countries("United States"))
        data.add(Countries("Japan"))
        data.add(Countries("China"))
        data.add(Countries("Belgium"))
        data.add(Countries("Canada"))*/

        // create a listener in order to access to the details for each country
       /* val onClickListener = View.OnClickListener { clickedRowView ->
            val clickedCountry: Countries = data[clickedRowView.tag as Int]
            Toast.makeText(this, "Clicked : " + clickedCountry.Country,
                                                                        Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CountryData::class.java)

            val message = clickedCountry.Country
            intent.putExtra("countryName", message)

            startActivity(intent)
        }

        // set the adapter in order to set the recycler view (in activity_data)
        activity_data_list_countries.adapter = CountriesAdapter(this, data,
                                                                                    onClickListener)

        // change this for webservice uses ?
        //activity_data_list_countries.setHasFixedSize(true)

        //recycler view appearance
        activity_data_list_countries.layoutManager = LinearLayoutManager(this)*/

    }

}
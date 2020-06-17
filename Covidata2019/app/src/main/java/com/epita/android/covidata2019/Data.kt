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

        // we use an single instance of retrofit located in the RetrofitInstance class
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
                        val clickedCountry: Countries = clickedRowView.tag as Countries
                        Toast.makeText(this@Data, "Clicked : " + clickedCountry.Country,
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Data, CountryData::class.java)

                        val message = clickedCountry.Country
                        intent.putExtra("countryName", message)
                        val message_slug = clickedCountry.Slug
                        intent.putExtra("SlugName", message_slug)
                        startActivity(intent)
                    }

                    // set the adapter in order to set the recycler view (in activity_data)
                    activity_data_list_countries.adapter = CountriesAdapter(this@Data, res,
                        onClickListener)

                    // change this for webservice uses ?
                    activity_data_list_countries.setHasFixedSize(true)

                    //recycler view appearance
                    activity_data_list_countries.layoutManager = LinearLayoutManager(this@Data)

                }
            }
        }

        service.GetCountries().enqueue(wsCallback)

        val wsCallbackGlobal: Callback<gdata> = object : Callback<gdata> {
            override fun onFailure(call: Call<gdata>, t: Throwable) {
                // Code here what happens if calling the WebService fails
                Log.w("TAG", "WebService call failed")
            }
            override fun onResponse(call: Call<gdata>, response:
            Response<gdata>
            ) {
                if (response.code() == 200) {
                    // We got our data !
                    val res = response.body()!!
                    if (res.Global != null) {
                        Log.d("TAG", "WebService wordl success : " + res)
                        Confirmedcpt.text = res.Global.TotalConfirmed.toString()
                        Recoveredcpt.text = res.Global.TotalRecovered.toString()
                        Deathcpt.text = res.Global.TotalDeaths.toString()
                    }

                }
            }
        }
        service.GetWorldData().enqueue(wsCallbackGlobal)
    }

}
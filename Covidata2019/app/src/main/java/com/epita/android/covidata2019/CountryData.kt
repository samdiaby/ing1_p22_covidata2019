package com.epita.android.covidata2019

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_country_data.*
import kotlinx.android.synthetic.main.activity_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CountryData : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_data)

        // get the country name from the pressed button
        val originIntent = intent
        val countryName = originIntent.getStringExtra("countryName")
        val SlugName = originIntent.getStringExtra("SlugName")
        // set the name of the country selected from the list
        activity_country_data_country_text.text = countryName

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
        val wsCallback: Callback<List<AllDatas>> = object : Callback<List<AllDatas>> {
            override fun onFailure(call: Call<List<AllDatas>>, t: Throwable) {
                // Code here what happens if calling the WebService fails
                Log.w("TAG", "WebService call failed")
            }
            override fun onResponse(call: Call<List<AllDatas>>, response:
            Response<List<AllDatas>>
            ) {
                if (response.code() == 200) {
                    // We got our data !
                    val res = response.body()!!
                    if (res != null) {
                        Log.d("TAG", "WebService success : " + res.size)
                    }

                    activity_country_data_calendar?.setOnDateChangeListener {
                            view, year, month, dayOfMonth ->
                        val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
                        Toast.makeText(this@CountryData, msg, Toast.LENGTH_SHORT).show()

                        var calendarDate : String = "" + year + "-"

                        if (month + 1 < 10) {
                            calendarDate += "0" + (month + 1)
                        } else {
                            calendarDate += (month + 1)
                        }

                        if (dayOfMonth < 10) {
                            calendarDate += "-0" + dayOfMonth
                        } else {
                            calendarDate += "-" + dayOfMonth
                        }

                        Log.d("TAG", "Date : " + calendarDate)
                        Log.d("TAG", "res size : " + res.size)
                        var isInside : Boolean = false

                        for (data in res) {
                            Log.d("TAG", "Status : " + data.Status)
                            if (data.Date.substring(0, 10).equals(calendarDate) && data.Status.equals("confirmed")) {
                                Log.d("TAG", "Date : " + data.Date)
                                activity_country_data_confirmed_count.text = data.Cases.toString()
                                isInside = true
                                break;
                            }
                            else if (data.Date.substring(0, 10).equals(calendarDate) && data.Status.equals("deaths")) {
                                Log.d("TAG", "Date : " + data.Date)
                                activity_country_data_deaths_count.text = data.Cases.toString()
                                isInside = true
                                break;
                            }
                            else if (data.Date.substring(0, 10).equals(calendarDate) && data.Status.equals("recovered")) {
                                Log.d("TAG", "Date : " + data.Date)
                                activity_country_data_recovered_count.text = data.Cases.toString()
                                isInside = true
                                break;
                            }
                        }

                        if (!isInside) {
                            activity_country_data_confirmed_count.text = "No data yet"
                            activity_country_data_deaths_count.text = "No data yet"
                            activity_country_data_recovered_count.text = "No data yet"
                        }

                    }

                }
            }
        }
        val cal: Calendar = Calendar.getInstance()
        val year : String = cal.get(Calendar.YEAR).toString()
        val month : Int = cal.get(Calendar.MONTH)
        val day : Int = cal.get(Calendar.DAY_OF_MONTH)
        var calendarDate : String = "" + year + "-"

        if (month + 1 < 10) {
            calendarDate += "0" + (month + 1)
        } else {
            calendarDate += (month + 1)
        }

        if (day < 10) {
            calendarDate += "-0" + day
        } else {
            calendarDate += "-" + day
        }
        calendarDate += "T00:00:00Z";
        Log.w("TAG", "DateC" + calendarDate)
        Log.w("TAG", "SlugName" + SlugName)

        service.GetDatasFrom(countryName,"confirmed","2020-03-01T00:00:00Z",
            calendarDate).enqueue(wsCallback)
        /*service.GetDatasFrom(SlugName,"deaths","2020-03-01T00:00:00Z",
            "2020-04-01T00:00:00Z").enqueue(wsCallback)
        service.GetDatasFrom(SlugName,"recovered","2020-03-01T00:00:00Z",
            "2020-04-01T00:00:00Z").enqueue(wsCallback)*/
    }
}
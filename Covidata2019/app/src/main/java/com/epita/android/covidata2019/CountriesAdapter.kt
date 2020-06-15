package com.epita.android.covidata2019

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class CountriesAdapter(val context : Activity, val data : List<Countries>)
    : RecyclerView.Adapter<CountriesAdapter.viewholder>() {

    class viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val countryName : Button = itemView.findViewById(R.id.country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val review : View = LayoutInflater.from(context)
                            .inflate(R.layout.list_item_countries, parent, false)
        return viewholder(review)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val country : Countries = data[position]
        holder.countryName.text = country.name
    }
}
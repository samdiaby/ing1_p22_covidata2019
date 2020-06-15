package com.epita.android.covidata2019

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_countries.view.*

class CountriesAdapter(val context : Activity, val data : List<Countries>, val onClickListener : View.OnClickListener)
    : RecyclerView.Adapter<CountriesAdapter.viewholder>() {

    class viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val countryName : Button = itemView.findViewById(R.id.country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val review : View = LayoutInflater.from(context)
                            .inflate(R.layout.list_item_countries, parent, false)

        // execute the code for the button in the list (activity_data)
        review.country.setOnClickListener(onClickListener)

        return viewholder(review)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val country : Countries = data[position]

        // Set the item to put in the recycler view
        holder.countryName.text = country.name

        holder.itemView.country.tag = position
    }
}
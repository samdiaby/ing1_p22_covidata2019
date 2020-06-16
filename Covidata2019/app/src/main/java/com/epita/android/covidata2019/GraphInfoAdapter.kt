package com.epita.android.covidata2019

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GraphInfoAdapter(val context : Activity, val data : List<GraphInfo>)
    : RecyclerView.Adapter<GraphInfoAdapter.viewholder>() {

    class viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val date : TextView = itemView.findViewById(R.id.date)
        val stats : ProgressBar = itemView.findViewById(R.id.stats)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val review : View = LayoutInflater.from(context)
            .inflate(R.layout.list_item_graph, parent, false)

        return viewholder(review)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val infos : GraphInfo = data[position]

        // Set the item to put in the recycler view
        holder.date.text = infos.date
        holder.stats.progress = infos.stats
    }
}
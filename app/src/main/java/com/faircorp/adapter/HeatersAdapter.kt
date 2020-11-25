package com.faircorp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.model.dto.HeaterDto
import com.faircorp.model.listener.OnHeaterSelectedListener

class HeatersAdapter(val listener: OnHeaterSelectedListener) :
    RecyclerView.Adapter<HeatersAdapter.HeaterViewHolder>() {

    inner class HeaterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_item_heater_name)

        //        val power: TextView = view.findViewById(R.id.)
        val room: TextView = view.findViewById(R.id.txt_item_heater_room)
        val heaterStatus: TextView = view.findViewById(R.id.txt_item_heater_status)
    }

    private val items = mutableListOf<HeaterDto>()

    fun update(heaters: List<HeaterDto>) {
        items.clear()
        items.addAll(heaters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_heaters_item, parent, false)
        return HeaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaterViewHolder, position: Int) {
        val heater = items[position]
        holder.apply {
            name.text = heater.name
            room.text = heater.room.name
            heaterStatus.text = heater.heaterStatus.toString()
            itemView.setOnClickListener { listener.onHeaterSelected(heater.id) }
        }
    }

    override fun getItemCount(): Int = items.size
}
package com.faircorp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.model.dto.RoomDto
import com.faircorp.model.listener.OnRoomSelectedListener

class RoomsAdapter(val listener: OnRoomSelectedListener) :
    RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_room_item_name)
        val level: TextView = view.findViewById(R.id.txt_room_item_level)
        val type: TextView = view.findViewById(R.id.txt_room_item_type)
    }

    private val items = mutableListOf<RoomDto>()

    fun update(rooms: List<RoomDto>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            name.text = room.name
            level.text = room.level.toString()
            type.text = room.type
            itemView.setOnClickListener { listener.onRoomSelected((room.id)) }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onViewRecycled(holder: RoomsAdapter.RoomViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }
    }
}
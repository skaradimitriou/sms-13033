package com.stathis.movementsms.ui.choice.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.movementsms.R
import com.stathis.movementsms.listeners.ItemClickListener
import com.stathis.movementsms.ui.choice.model.MovementOption

class MovementOptionsAdapter(val itemClickListener : ItemClickListener) : ListAdapter<MovementOption, MovementOptionsViewHolder>(
    DiffUtilClass()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementOptionsViewHolder {
        return MovementOptionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movement_option_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: MovementOptionsViewHolder, position: Int) {
        holder.present(getItem(position),itemClickListener)
    }
}
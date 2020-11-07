package com.stathis.sms13033.ui.choice.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.sms13033.R
import com.stathis.sms13033.listeners.ItemClickListener
import com.stathis.sms13033.ui.choice.model.MovementOption

class MovementOptionsAdapter(val itemClickListener : ItemClickListener) : ListAdapter<MovementOption, MovementOptionsViewHolder >(DiffUtilClass()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementOptionsViewHolder {
        return MovementOptionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movement_option_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: MovementOptionsViewHolder, position: Int) {
        holder.present(getItem(position),itemClickListener)
    }
}
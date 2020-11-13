package com.stathis.movementsms.ui.choice.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stathis.movementsms.listeners.ItemClickListener
import com.stathis.movementsms.ui.choice.model.MovementOption
import kotlinx.android.synthetic.main.movement_option_item_row.view.*

class MovementOptionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun present (movementOption: MovementOption, itemClickListener: ItemClickListener) {
        itemView.movement_option_img.setImageResource(movementOption.movementPhoto)
        itemView.movement_option_txt.text = movementOption.movementName

        itemView.setOnClickListener {
            itemClickListener.itemClicked(movementOption)
        }
    }
}
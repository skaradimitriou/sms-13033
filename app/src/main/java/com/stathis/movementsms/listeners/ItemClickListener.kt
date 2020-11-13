package com.stathis.movementsms.listeners

import com.stathis.movementsms.ui.choice.model.MovementOption

interface ItemClickListener {

    fun itemClicked(movementOption: MovementOption)
}
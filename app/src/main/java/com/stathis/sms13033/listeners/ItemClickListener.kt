package com.stathis.sms13033.listeners

import com.stathis.sms13033.ui.choice.model.MovementOption

interface ItemClickListener {

    fun itemClicked(movementOption: MovementOption)
}
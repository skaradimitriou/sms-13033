package com.stathis.movementsms.listeners

import com.stathis.movementsms.ui.choice.model.MovementOption

interface ChoiceActivityListener {

    fun sendSMS(movementOption: MovementOption)
}
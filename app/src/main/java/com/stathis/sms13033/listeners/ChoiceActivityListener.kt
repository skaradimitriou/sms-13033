package com.stathis.sms13033.listeners

import com.stathis.sms13033.ui.choice.model.MovementOption

interface ChoiceActivityListener {

    fun sendSMS(movementOption: MovementOption)
}
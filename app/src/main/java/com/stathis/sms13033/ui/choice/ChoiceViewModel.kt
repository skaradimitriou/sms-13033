package com.stathis.sms13033.ui.choice

import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.stathis.sms13033.listeners.ChoiceActivityListener
import com.stathis.sms13033.listeners.ItemClickListener
import com.stathis.sms13033.ui.choice.model.MovementOption
import com.stathis.sms13033.ui.choice.recycler.MovementOptionsAdapter
import com.stathis.sms13033.ui.choice.repository.ChoiceRepo


class ChoiceViewModel : ViewModel(), ItemClickListener {

    val adapter = MovementOptionsAdapter(this)
    private lateinit var choiceActivityListener: ChoiceActivityListener

    init {
        getMovementOptions()
    }

    fun initActivityListener(choiceActivityListener: ChoiceActivityListener) {
        this.choiceActivityListener = choiceActivityListener
    }

    private fun getMovementOptions() {

        val choiceRepo = ChoiceRepo()
        adapter.submitList(choiceRepo.getMovementChoices())
    }

    override fun itemClicked(movementOption: MovementOption) {
        choiceActivityListener.sendSMS(movementOption)
    }

    fun sendSMSMessage(movementOption: MovementOption, fullName: String, address: String) {
        try {
            val sms = SmsManager.getDefault()
            sms.sendTextMessage(
                "13033",
                null,
                "${movementOption.movementId} $fullName $address",
                null,
                null
            )
        } catch (e: Exception) {
            Log.d("e", e.toString())
        }

        val number = "12346556" // The number on which you want to send SMS
    }
}
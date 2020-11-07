package com.stathis.sms13033.ui.choice

import androidx.lifecycle.ViewModel
import com.stathis.sms13033.listeners.ChoiceActivityListener
import com.stathis.sms13033.listeners.ItemClickListener
import com.stathis.sms13033.ui.choice.model.MovementOption
import com.stathis.sms13033.ui.choice.recycler.MovementOptionsAdapter
import com.stathis.sms13033.ui.choice.repository.ChoiceRepo

class ChoiceViewModel : ViewModel(), ItemClickListener {

    val adapter = MovementOptionsAdapter(this)
    private lateinit var choiceActivityListener: ChoiceActivityListener

    init{
        getMovementOptions()
    }

    fun initActivityListener(choiceActivityListener: ChoiceActivityListener){
        this.choiceActivityListener = choiceActivityListener
    }

    private fun getMovementOptions(){

        val choiceRepo = ChoiceRepo()
        adapter.submitList(choiceRepo.getMovementChoices())
    }

    override fun itemClicked(movementOption: MovementOption) {
        choiceActivityListener.sendSMS(movementOption)
    }

}
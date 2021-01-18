package com.stathis.movementsms.ui.choice

import androidx.lifecycle.ViewModel
import com.stathis.movementsms.listeners.ChoiceActivityListener
import com.stathis.movementsms.listeners.ItemClickListener
import com.stathis.movementsms.ui.choice.model.MovementOption
import com.stathis.movementsms.ui.choice.recycler.MovementOptionsAdapter
import com.stathis.movementsms.ui.choice.repository.ChoiceRepo


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
}
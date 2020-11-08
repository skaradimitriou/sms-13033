package com.stathis.sms13033.ui.choice

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import com.stathis.sms13033.R
import com.stathis.sms13033.abstraction.AbstractActivity
import com.stathis.sms13033.listeners.ChoiceActivityListener
import com.stathis.sms13033.ui.choice.model.MovementOption
import kotlinx.android.synthetic.main.activity_choice.*
import kotlinx.android.synthetic.main.activity_main.*

class ChoiceActivity : AbstractActivity(R.layout.activity_choice),ChoiceActivityListener{

    private lateinit var fullName : String
    private lateinit var address : String
    private lateinit var viewModel : ChoiceViewModel

    override fun initLayout() {
        viewModel = ViewModelProvider(this).get(ChoiceViewModel::class.java)
    }

    override fun runOperation() {
        fullName = intent.getStringExtra("fullName") ?: ""
        address = intent.getStringExtra("address") ?: ""

        choices_recycler.adapter = viewModel.adapter
        viewModel.initActivityListener(this)
    }

    override fun stopOperation() {
        //
    }

    override fun sendSMS(movementOption: MovementOption) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:13033"))
        intent.putExtra("sms_body", "ΜΕΤΑΚΙΝΗΣΗ ${movementOption.movementId} $fullName $address")
        startActivity(intent)
    }

}
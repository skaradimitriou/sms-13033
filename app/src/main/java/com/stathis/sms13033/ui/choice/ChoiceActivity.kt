package com.stathis.sms13033.ui.choice

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.sms13033.R
import com.stathis.sms13033.abstraction.AbstractActivity
import com.stathis.sms13033.listeners.ChoiceActivityListener
import com.stathis.sms13033.ui.choice.model.MovementOption
import kotlinx.android.synthetic.main.activity_choice.*
import kotlinx.android.synthetic.main.movement_option_dialog.view.*

class ChoiceActivity : AbstractActivity(R.layout.activity_choice), ChoiceActivityListener {

    private lateinit var fullName: String
    private lateinit var address: String
    private val SEND_SMS_REQ_CODE = 1
    private lateinit var viewModel: ChoiceViewModel

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
        sendSMSMessage(movementOption)
    }

    private fun sendSMSMessage(movementOption: MovementOption) {
        val movementOptionView =
            LayoutInflater.from(this).inflate(R.layout.movement_option_dialog, null)
        val builder = MaterialAlertDialogBuilder(this).setView(movementOptionView)
        builder.show()
        movementOptionView.mov_option_img.setImageResource(movementOption.movementPhoto)
        movementOptionView.mov_option_reason.text = "Μετακίνηση ${movementOption.movementId}"
        movementOptionView.mov_option_desc.text = movementOption.movementName
        movementOptionView.mov_option_button.setOnClickListener {

            //sending SMS
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:13033"))
            intent.putExtra("sms_body", "${movementOption.movementId} $fullName $address")
            startActivity(intent)
        }
    }


}
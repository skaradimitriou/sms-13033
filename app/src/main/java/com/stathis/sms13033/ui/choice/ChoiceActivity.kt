package com.stathis.sms13033.ui.choice

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.sms13033.R
import com.stathis.sms13033.abstraction.AbstractActivity
import com.stathis.sms13033.listeners.ChoiceActivityListener
import com.stathis.sms13033.ui.choice.model.MovementOption
import kotlinx.android.synthetic.main.activity_choice.*

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            SEND_SMS_REQ_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    override fun sendSMS(movementOption: MovementOption) {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)

        if (permission == PackageManager.PERMISSION_GRANTED) {
            sendSMSMessage(movementOption)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                SEND_SMS_REQ_CODE
            )
        }

    }

    private fun sendSMSMessage(movementOption: MovementOption) {
        val builder = MaterialAlertDialogBuilder(this)
        builder.setTitle(R.string.movement_reason_sure)
        builder.setMessage("Έχετε επιλέξει SMS για: ${movementOption.movementName}. Είστε σίγουρος/η;")
            .setPositiveButton("Αποστολή") { dialog, id ->

                //sending SMS
                viewModel.sendSMSMessage(movementOption, fullName, address)
                viewModel.showSuccessMessage(this)
            }
            .setNegativeButton(
                "Άκυρο"
            ) { dialog, id ->
                dialog.dismiss()
            }
        builder.show()
    }


}
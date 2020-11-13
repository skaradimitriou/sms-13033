package com.stathis.movementsms.ui.user

import android.content.Intent
import android.text.Editable
import androidx.lifecycle.ViewModelProvider
import com.stathis.movementsms.R
import com.stathis.movementsms.abstraction.AbstractActivity
import com.stathis.movementsms.ui.choice.ChoiceActivity
import kotlinx.android.synthetic.main.activity_user.*

class MovementActivity : AbstractActivity(R.layout.activity_user) {

    private lateinit var viewModel: MovementViewModel

    override fun initLayout() {
        viewModel = ViewModelProvider(this).get(MovementViewModel::class.java)

        // init Room Db
        viewModel.initDatabase(this)

        checkSwitchPermission()

        if (data_switch.isChecked && viewModel.dbExists()) {
            getUserData()
        }
    }

    override fun runOperation() {

        about.setOnClickListener {
            viewModel.showAboutDialog(this)
        }

        data_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // check if input is different than
                if (viewModel.dbExists()) {
                    updateUser()
                } else {
                    saveUser()
                }
            } else {
                when (viewModel.userExists(fullName.text.toString(), address.text.toString())) {
                    true -> {
                        deleteUser()
                    }
                }
            }
        }

        user_screen_btn.setOnClickListener {
            if (!fullName.text.toString().isNullOrEmpty() && !address.text.toString()
                    .isNullOrEmpty()
            ) {
                startActivity(Intent(this, ChoiceActivity::class.java).apply {
                    this.putExtra("fullName", fullName.text.toString())
                    this.putExtra("address", address.text.toString())
                })
            }
        }
    }

    override fun stopOperation() {
        //
    }

    private fun getUserData() {
        name_field.editText?.text =
            Editable.Factory.getInstance().newEditable(viewModel.getUserFullName())
        address_field.editText?.text =
            Editable.Factory.getInstance().newEditable(viewModel.getUserAddress())
    }

    private fun saveUser() {
        viewModel.saveUser(
            fullName.text.toString(),
            address.text.toString(),
            data_switch.isChecked
        )
    }

    private fun updateUser() {
        viewModel.updateUserData(
            fullName.text.toString(),
            address.text.toString(),
            data_switch.isChecked
        )
    }

    private fun deleteUser() {
        viewModel.deleteUser(
            fullName.text.toString(),
            address.text.toString(),
            data_switch.isChecked
        )
        name_field.editText?.text =
            Editable.Factory.getInstance().newEditable("")
        address_field.editText?.text =
            Editable.Factory.getInstance().newEditable("")
    }

    private fun checkSwitchPermission() {
        if (viewModel.dbExists()) {
            data_switch.isChecked = viewModel.getUser().saveMyData
        } else {
            data_switch.isChecked = false
        }
    }
}
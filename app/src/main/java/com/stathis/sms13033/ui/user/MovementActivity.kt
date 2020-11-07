package com.stathis.sms13033.ui.user

import android.content.Intent
import android.text.Editable
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProvider
import com.stathis.sms13033.R
import com.stathis.sms13033.abstraction.AbstractActivity
import com.stathis.sms13033.ui.choice.ChoiceActivity
import kotlinx.android.synthetic.main.activity_main.*

class MovementActivity : AbstractActivity(R.layout.activity_main) {

    private lateinit var viewModel: MovementViewModel

    override fun initLayout() {
        viewModel = ViewModelProvider(this).get(MovementViewModel::class.java)

        if (data_switch.isChecked) {
            name_field.editText?.text =
                Editable.Factory.getInstance().newEditable("Stathis Karadimitriou")
            address_field.editText?.text =
                Editable.Factory.getInstance().newEditable("Sachini 4")
        }
    }

    override fun runOperation() {

        data_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                name_field.editText?.text =
                    Editable.Factory.getInstance().newEditable("Stathis Karadimitriou")
                address_field.editText?.text =
                    Editable.Factory.getInstance().newEditable("Sachini 4")
            } else {
                name_field.editText?.text =
                    Editable.Factory.getInstance().newEditable("")
                address_field.editText?.text =
                    Editable.Factory.getInstance().newEditable("")
            }
        }

        user_screen_btn.setOnClickListener {
            startActivity(Intent(this, ChoiceActivity::class.java).apply {
                this.putExtra("fullName", fullName.text.toString())
                this.putExtra("address", address.text.toString())
            })
        }
    }

    override fun stopOperation() {
        //
    }

}
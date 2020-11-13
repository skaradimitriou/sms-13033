package com.stathis.movementsms.abstraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity(contentLayoutId : Int) : AppCompatActivity(contentLayoutId) {

    abstract fun initLayout()
    abstract fun runOperation()
    abstract fun stopOperation()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        initLayout()
    }

    override fun onPostResume() {
        super.onPostResume()

        runOperation()
    }

    override fun onStop() {
        stopOperation()

        super.onStop()
    }
}
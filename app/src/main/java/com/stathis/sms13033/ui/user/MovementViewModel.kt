package com.stathis.sms13033.ui.user

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.sms13033.R
import com.stathis.sms13033.database.UsersDatabase
import com.stathis.sms13033.ui.user.model.User


class MovementViewModel : ViewModel() {

    lateinit var usersDatabase: UsersDatabase
    private lateinit var fullName: String
    private lateinit var address: String
    private lateinit var user: User

    init {
        Log.i("ViewModel Created", "ViewModel Created!")
    }

    fun initDatabase(context: Context) {
        usersDatabase = UsersDatabase.getDatabase(context)
    }

    fun getUserFullName(): String {
        for (i in usersDatabase.usersDao().getAll()) {
            fullName = i.fullName
        }
        return fullName
    }

    fun getUserAddress(): String {
        for (i in usersDatabase.usersDao().getAll()) {
            address = i.address
        }
        return address
    }

    fun dbExists(): Boolean {
        return !usersDatabase.usersDao().getAll().isNullOrEmpty()
    }

    fun saveUser(fullName: String, address: String, saveMyData: Boolean) {
        usersDatabase.usersDao().insert(User(1, fullName, address, saveMyData))
    }

    fun updateUserData(fullName: String, address: String, saveMyData: Boolean) {
        val user = User(1, fullName, address, saveMyData)
        for (i in usersDatabase.usersDao().getAll()) {
            if (i.fullName == user.fullName || i.address == user.address) {
                updateUser(user)
            }
        }
    }

    fun updateUser(user: User) {
        usersDatabase.usersDao().update(user)
    }

    fun deleteUser(fullName: String, address: String, saveMyData: Boolean) {
        usersDatabase.usersDao().delete(User(1, fullName, address, saveMyData))
    }

    fun userExists(fullName: String, address: String): Boolean {
        for (i in usersDatabase.usersDao().getAll()) {
            if (fullName == i.fullName && address == i.address) {
                return true
            }
        }
        return false
    }

    fun getUser(): User {
        for (i in usersDatabase.usersDao().getAll()) {
            user = i
        }
        return user
    }

    fun showAboutDialog(context: Context) {
        val builder = MaterialAlertDialogBuilder(context)
        builder.setTitle(context.resources.getString(R.string.about_app_header))
        builder.setMessage(context.resources.getString(R.string.about_app))
        builder.show()
    }

}
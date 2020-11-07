package com.stathis.sms13033.database

import androidx.room.*
import com.stathis.sms13033.ui.user.model.User

@Dao
interface UserDao {

    @Query("SELECT 1 FROM users")
    fun getFullName(): String

    @Query("SELECT 1 FROM users")
    fun getAddress(): String

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
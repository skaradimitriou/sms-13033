package com.stathis.sms13033.database

import androidx.room.*
import com.stathis.sms13033.ui.user.model.User

@Dao
interface UserDao {

    @Query("SELECT fullName FROM users")
    fun getFullName(): String

    @Query("SELECT address FROM users")
    fun getAddress(): String

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
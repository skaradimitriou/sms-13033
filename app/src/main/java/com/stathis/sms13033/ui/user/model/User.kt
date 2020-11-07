package com.stathis.sms13033.ui.user.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id : Int = 1,

    @ColumnInfo(name = "fullName")
    val fullName: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "permission")
    val saveMyData: Boolean
)
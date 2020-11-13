package com.stathis.movementsms.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stathis.movementsms.ui.user.model.User


@Database(entities = [User::class] , version = 4, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "users_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
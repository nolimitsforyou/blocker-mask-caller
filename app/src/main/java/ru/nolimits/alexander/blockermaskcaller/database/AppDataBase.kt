package ru.nolimits.alexander.blockermaskcaller.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Mask::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun masksDao(): MaskDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "masks_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
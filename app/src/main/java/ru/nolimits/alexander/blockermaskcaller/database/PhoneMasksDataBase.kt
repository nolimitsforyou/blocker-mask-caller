package ru.nolimits.alexander.blockermaskcaller.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Mask::class), version = 1)
abstract class PhoneMasksDataBase : RoomDatabase() {

    abstract fun masksDao(): MaskDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PhoneMasksDataBase? = null

        fun getDataBase(context: Context): PhoneMasksDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneMasksDataBase::class.java,
                    "phone_masks_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
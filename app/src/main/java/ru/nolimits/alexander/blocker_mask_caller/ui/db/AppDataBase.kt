package ru.nolimits.alexander.blocker_mask_caller.ui.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Mask::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun masksDao(): MasksDao
}
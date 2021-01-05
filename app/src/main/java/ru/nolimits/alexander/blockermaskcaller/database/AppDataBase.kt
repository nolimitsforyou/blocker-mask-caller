package ru.nolimits.alexander.blockermaskcaller.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Mask::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun masksDao(): MaskDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {

                    var maskDao = database.masksDao()
                    maskDao.deleteAll()
                    var mask = Mask(numeric = "999888", title = "Петуханыч")
                    maskDao.insertNewMask(mask)
                    mask = Mask(numeric = "123456", title = "Братаныч")
                    maskDao.insertNewMask(mask)
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "masks_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
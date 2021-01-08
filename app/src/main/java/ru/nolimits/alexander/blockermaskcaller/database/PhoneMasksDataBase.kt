package ru.nolimits.alexander.blockermaskcaller.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Mask::class), version = 1)
abstract class PhoneMasksDataBase : RoomDatabase() {

    abstract val masksDao: MaskDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                //тут можно получать данные из сети если это предусмотрено
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: PhoneMasksDataBase? = null

        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): PhoneMasksDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneMasksDataBase::class.java,
                    "phone_masks_database"
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
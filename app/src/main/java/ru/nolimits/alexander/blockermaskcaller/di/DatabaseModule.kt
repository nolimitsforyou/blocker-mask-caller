package ru.nolimits.alexander.blockermaskcaller.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.nolimits.alexander.blockermaskcaller.database.PhoneMasksDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): PhoneMasksDataBase {
        return Room.databaseBuilder(
            appContext,
            PhoneMasksDataBase::class.java,
            "logging.db"
        ).build()
    }
}
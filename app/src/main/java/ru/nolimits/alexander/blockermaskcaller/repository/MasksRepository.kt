package ru.nolimits.alexander.blockermaskcaller.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.database.PhoneMasksDataBase

/**
 * Repository  используется в случае если есть несколько источников данных (Network, Local)
 * в нашем случае данные хранятся только в БД
 */

class MasksRepository(private val database: PhoneMasksDataBase) {

    val allMasks: Flow<List<Mask>> = database.masksDao.getAllMasks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(mask: Mask) {
        database.masksDao.insertNewMask(mask)
    }
}
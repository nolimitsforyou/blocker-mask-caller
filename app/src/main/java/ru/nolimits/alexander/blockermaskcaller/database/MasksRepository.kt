package ru.nolimits.alexander.blockermaskcaller.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 * Repository  используется в случае если есть несколько источников данных (Network, Local)
*/

class MasksRepository(private val maskDao: MaskDao) {

    val allMasks: Flow<List<Mask>> = maskDao.getAllMasks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(mask: Mask) {
        maskDao.insertNewMask(mask)
    }
}
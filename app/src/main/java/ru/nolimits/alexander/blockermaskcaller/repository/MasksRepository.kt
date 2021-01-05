package ru.nolimits.alexander.blockermaskcaller.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.database.MaskDao

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
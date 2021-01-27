package ru.nolimits.alexander.blockermaskcaller.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.database.PhoneMasksDataBase

/**
 * Repository  используется в случае если есть несколько источников данных (Network, Local)
 * в нашем случае данные хранятся только в БД
 */

class MasksRepository(private val database: PhoneMasksDataBase) {

    val allMasks: Flow<List<Mask>> = database.masksDao.getAllMasks()

    suspend fun insert(mask: Mask) {
        withContext(Dispatchers.IO) {
            database.masksDao.insertMask(mask)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            database.masksDao.deleteAll()
        }
    }

    suspend fun getMaskById(idMask: Int): Mask {
        return withContext(Dispatchers.IO) {
            database.masksDao.getMaskById(idMask).first()
        }
    }
}
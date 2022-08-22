package ru.nolimits.alexander.blockermaskcaller.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MasksRepository @Inject constructor(private val masksDao: MaskDao) {

    val allMasks: Flow<List<Mask>> = masksDao.getAllMasks()

    suspend fun insert(mask: Mask) {
        withContext(Dispatchers.IO) {
            masksDao.insertMask(mask)
        }
    }

    suspend fun update(mask: Mask) {
        withContext(Dispatchers.IO) {
            masksDao.updateMask(mask)
        }
    }

    suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            masksDao.deleteMask(id)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            masksDao.deleteAll()
        }
    }

    suspend fun deleteSelected(listIds: List<Int>) {
        withContext(Dispatchers.IO) {
            masksDao.deleteSelected(listIds)
        }
    }

    suspend fun getMaskByNumber(number: Int): Mask? {
        return withContext(Dispatchers.IO) {
            masksDao.getMaskByNumeric(number)
        }
    }

    suspend fun getCountOfMasks(): Int {
        return withContext(Dispatchers.IO) {
            masksDao.getMasksCount()
        }
    }
}
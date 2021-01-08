package ru.nolimits.alexander.blockermaskcaller.repository

import kotlinx.coroutines.flow.Flow
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.database.PhoneMasksDataBase

/**
 * Repository  используется в случае если есть несколько источников данных (Network, Local)
 * в нашем случае данные хранятся только в БД
 */

class MasksRepository(private val database: PhoneMasksDataBase) {

    val allMasks: Flow<List<Mask>> = database.masksDao.getAllMasks()

}
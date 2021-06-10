package ru.nolimits.alexander.blockermaskcaller

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.nolimits.alexander.blockermaskcaller.database.PhoneMasksDataBase
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository


class PhoneMasksApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val dataBase by lazy { PhoneMasksDataBase.getDataBase(this, applicationScope) }
    val repository by lazy { MasksRepository(dataBase) }

}
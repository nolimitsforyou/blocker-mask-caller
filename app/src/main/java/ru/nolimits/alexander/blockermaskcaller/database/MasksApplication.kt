package ru.nolimits.alexander.blockermaskcaller.database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository

class MasksApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val dataBase by lazy { AppDataBase.getDataBase(this, applicationScope) }
    val repository by lazy { MasksRepository(dataBase.masksDao()) }
}
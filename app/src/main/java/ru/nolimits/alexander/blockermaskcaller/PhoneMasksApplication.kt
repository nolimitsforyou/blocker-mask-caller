package ru.nolimits.alexander.blockermaskcaller

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhoneMasksApplication : Application() {

//    private val applicationScope = CoroutineScope(SupervisorJob())
//    private val dataBase by lazy { PhoneMasksDataBase.getDataBase(this, applicationScope) }
//    val repository by lazy { MasksRepository(dataBase) }

}
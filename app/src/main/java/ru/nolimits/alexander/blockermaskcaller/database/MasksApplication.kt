package ru.nolimits.alexander.blockermaskcaller.database

import android.app.Application
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository

class MasksApplication : Application() {

    val dataBase by lazy { AppDataBase.getDataBase(this) }
    val repository by lazy { MasksRepository(dataBase.masksDao()) }
}
package ru.nolimits.alexander.blockermaskcaller.database

import android.app.Application

class MasksApplication : Application() {

    val dataBase by lazy { AppDataBase.getDataBase(this) }
}
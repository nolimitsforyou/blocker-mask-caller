package ru.nolimits.alexander.blocker_mask_caller.ui.db

import android.app.Application

class MasksApplication : Application() {

    val dataBase by lazy { AppDataBase.getDataBase(this) }
}
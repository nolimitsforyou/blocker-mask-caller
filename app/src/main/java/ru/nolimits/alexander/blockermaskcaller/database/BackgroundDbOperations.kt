package ru.nolimits.alexander.blockermaskcaller.database

import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication

object BackgroundDbOperations {

    private val app = PhoneMasksApplication()

    //TODO возвращаемый тип Boolean?
    suspend fun checkNumber(number: Int) {
        val mask = app.repository.getMaskByNumber(number)
    }
}
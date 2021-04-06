package ru.nolimits.alexander.blockermaskcaller.database

import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication

object BackgroundDbOperations {

    private val app = PhoneMasksApplication()

    suspend fun checkNumber(number: Int): Mask? {
       return app.repository.getMaskByNumber(number)
    }
}
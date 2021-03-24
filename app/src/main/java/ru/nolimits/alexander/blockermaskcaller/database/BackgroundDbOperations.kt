package ru.nolimits.alexander.blockermaskcaller.database

import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication

object BackgroundDbOperations {

    private val app = PhoneMasksApplication()

    suspend fun test(number: Int) {
        app.repository.getMaskByNumber(number)
    }
}
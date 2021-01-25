package ru.nolimits.alexander.blockermaskcaller.shared

import ru.nolimits.alexander.blockermaskcaller.database.Mask

interface Communicator {

    fun sendData(mask: Mask)
}
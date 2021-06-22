package ru.nolimits.alexander.blockermaskcaller.shared

import ru.nolimits.alexander.blockermaskcaller.data.Mask

interface Communicator {

    fun sendData(mask: Mask)
}
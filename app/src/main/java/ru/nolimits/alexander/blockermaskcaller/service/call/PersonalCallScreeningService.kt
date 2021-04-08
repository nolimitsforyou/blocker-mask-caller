package ru.nolimits.alexander.blockermaskcaller.service.call

import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.database.BackgroundDbOperations.checkNumber

class PersonalCallScreeningService : CallScreeningService() {

    override fun onScreenCall(callDetails: Call.Details) {

        val phoneNumber: Int = getPhoneNumber(callDetails)
        var response = CallResponse.Builder()
        response = handlePhoneCall(response, phoneNumber)
        respondToCall(callDetails, response.build())
    }

    private fun handlePhoneCall(
        response: CallResponse.Builder,
        phoneNumber: Int
    ): CallResponse.Builder {

        GlobalScope.launch {
            if (checkNumber(phoneNumber) != null) {
                //TODO блокировать
                response.apply {
                    setRejectCall(true)
                    setDisallowCall(true)
                    setSkipCallLog(false)
                }
                Log.d("CallReceiver", "number found")
            } else {
                Log.d("CallReceiver", "number not found")
            }
        }
        return response
    }

    private fun getPhoneNumber(callDetails: Call.Details): Int {
        return callDetails.handle.schemeSpecificPart.toInt()
    }
}
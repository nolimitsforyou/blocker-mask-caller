package ru.nolimits.alexander.blockermaskcaller.service.call

import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log
import kotlinx.coroutines.*
import ru.nolimits.alexander.blockermaskcaller.database.BackgroundDbOperations.checkNumber

class PersonalCallScreeningService : CallScreeningService() {

    override fun onScreenCall(callDetails: Call.Details) {

        val phoneNumber: Int = getPhoneNumber(callDetails)

        var response = CallResponse.Builder()

        GlobalScope.launch {
            val job = launch {
                response = handlePhoneCall(response, phoneNumber)
            }
            job.join()
            //после ожидания завершения корутины выполняем действие со звонком
            respondToCall(callDetails, response.build())
        }
    }

    private suspend fun handlePhoneCall(
        response: CallResponse.Builder,
        phoneNumber: Int
    ): CallResponse.Builder {

        val job = GlobalScope.launch {
            if (checkNumber(phoneNumber) != null) {
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
        job.join()
        return response
    }

    private fun getPhoneNumber(callDetails: Call.Details): Int {
        //todo заюзать регекс
        return Regex("""[^0-9]""")
            .replace(callDetails.handle.schemeSpecificPart, "")
            .trim()
            .toInt()
    }
}
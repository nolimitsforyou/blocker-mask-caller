package ru.nolimits.alexander.blockermaskcaller.service.call

import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.data.MasksRepository
import javax.inject.Inject

@AndroidEntryPoint
class PersonalCallScreeningService : CallScreeningService() {

    @Inject lateinit var repository: MasksRepository

    @OptIn(DelicateCoroutinesApi::class)
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


    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun handlePhoneCall(
        response: CallResponse.Builder,
        phoneNumber: Int
    ): CallResponse.Builder {

        val job = GlobalScope.launch {
            if (repository.getMaskByNumber(phoneNumber) != null) {
                response.apply {
                    setRejectCall(true)
                    setDisallowCall(true)
                    setSkipCallLog(false)
                }
                Log.d("PersonalCallScreeningService", "number found")
            } else {
                Log.d("PersonalCallScreeningService", "number not found")
            }
        }
        job.join()
        return response
    }

    private fun getPhoneNumber(callDetails: Call.Details): Int {
        return Regex("""[^0-9]""")
            .replace(callDetails.handle.schemeSpecificPart, "")
            .substring(0, 7)
            .trim()
            .toInt()
    }
}
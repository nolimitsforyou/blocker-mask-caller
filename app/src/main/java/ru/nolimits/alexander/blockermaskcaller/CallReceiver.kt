package ru.nolimits.alexander.blockermaskcaller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import ru.nolimits.alexander.blockermaskcaller.service.call.PersonalCallScreeningService

class CallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        if (state.equals(TelephonyManager.CALL_STATE_RINGING)) {
            val serviceIntent = Intent(context, PersonalCallScreeningService::class.java)
            context.startService(serviceIntent)
            //TODO надо ещё проверять разрешения на READ_PHONE_STATE
        }
    }
}
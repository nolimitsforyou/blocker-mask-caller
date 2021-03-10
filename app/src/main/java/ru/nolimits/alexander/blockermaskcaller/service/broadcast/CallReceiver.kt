package ru.nolimits.alexander.blockermaskcaller.service.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import kotlin.math.log

class CallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            //TODO обработка номера и запрос в БД за ним
            Log.d("CallReceiver", "get_call")
        }
    }
}
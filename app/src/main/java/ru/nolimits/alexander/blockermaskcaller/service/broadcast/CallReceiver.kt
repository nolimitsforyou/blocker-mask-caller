package ru.nolimits.alexander.blockermaskcaller.service.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.database.BackgroundDbOperations.checkNumber

class CallReceiver : BroadcastReceiver() {

    @SuppressLint("SoonBlockedPrivateApi")
    override fun onReceive(context: Context, intent: Intent) {

        val phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

        if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            val incomingNumber =
                intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                    .substring(0, 7)
                    .toInt()

            //обработка входящего номера и запрос в БД по нему
            GlobalScope.launch {
                if (checkNumber(incomingNumber) != null) {
                    //TODO блокировать
                    Log.d("CallReceiver", "number found")
                } else {
                    Log.d("CallReceiver", "number not found")
                }
            }
        }
    }
}
package net.zeta.allonsy.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.core.content.ContextCompat

object Device {
    /** Returns the consumer friendly device name  */
    fun getCurrentDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else capitalize(manufacturer) + " " + model
    }

    private fun capitalize(str: String): String {
        if (TextUtils.isEmpty(str)) {
            return str
        }
        val arr = str.toCharArray()
        var capitalizeNext = true
        var phrase = ""
        for (c in arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c)
                capitalizeNext = false
                continue
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true
            }
            phrase += c
        }
        return phrase
    }

    @Suppress("DEPRECATION")
    @SuppressLint("HardwareIds")
    fun getImei(application: Application): String {
        return if(ContextCompat.checkSelfPermission(application, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                (application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).imei
            } else{
                (application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
            }
        }else{
            ""
        }

    }
}
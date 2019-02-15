package net.zeta.allonsy.services.dto

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject

class Helper {
    //Need to fucking remake this logic
    companion object {
        fun createRegisterPayload(imei: String,deviceName: String) : JsonObject {
            val gson = Gson()
            val requestBody = JsonObject()
            val payload = mapOf("imei" to imei, "deviceName" to deviceName)
            requestBody.addProperty("payload", gson.toJson(payload))
            return requestBody
        }

        fun createLoginPayload(name: String,email: String,provider: String,providerId: String) : JsonObject {
            val gson = Gson()
            val body = JsonObject()
            val social = mapOf("provider" to provider, "providerid" to providerId)
            val payload = mapOf("type" to "social", "social" to  social)
            body.addProperty("payload", gson.toJson(payload))
            return body
        }

    }
}

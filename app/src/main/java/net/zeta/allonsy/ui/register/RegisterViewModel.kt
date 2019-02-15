package net.zeta.allonsy.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import net.zeta.allonsy.contracts.Actor
import net.zeta.allonsy.managers.NetworkManager
import net.zeta.allonsy.services.dto.Helper
import net.zeta.allonsy.utils.Constants.identityId
import net.zeta.allonsy.utils.Constants.registered
import net.zeta.allonsy.utils.Device
import net.zeta.allonsy.utils.Messages
import timber.log.Timber
import org.jetbrains.anko.defaultSharedPreferences


class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var deviceName: String
    private lateinit var imei: String

    fun registerInfos(application: Application, registrationListener: Actor){
        deviceName = Device.getCurrentDeviceName()
        imei = Device.getImei(application = application)

        val isRegistered: Boolean = application.defaultSharedPreferences.getBoolean(registered, false)

        if(!isRegistered){
            NetworkManager.allonsyService.registerDevice(Helper.createRegisterPayload(imei = imei,deviceName = deviceName))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeBy(
                onNext = {
                    response ->
                        application.defaultSharedPreferences.edit().putString(identityId, response["_id"].asString).apply()
                        application.defaultSharedPreferences.edit().putBoolean(registered, true).apply()
                        registrationListener.message( null ,Messages.REGISTRATION_COMPLETE)
                },
                onError = {
                    error ->
                        Timber.e("Error Happened %s" , error.localizedMessage)
                }
            )
        }else{
            registrationListener.message( null ,Messages.REGISTRATION_COMPLETE)
        }


    }

}

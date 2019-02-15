package net.zeta.allonsy

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import net.zeta.allonsy.contracts.Actor
import net.zeta.allonsy.managers.NetworkManager
import net.zeta.allonsy.services.dto.Helper
import net.zeta.allonsy.utils.Messages

class AppViewModel(application: Application) : AndroidViewModel(application){
    fun login(name: String, email: String, provider: String, providerId: String,loginListener: Actor){
        NetworkManager.allonsyService.login(Helper.createLoginPayload(name= name,email = email,provider = provider,providerId = providerId))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    response -> loginListener.message(actor = null,message =  if (response["newUser"].asBoolean) Messages.LOGIN_COMPLETE_NEW_USER else Messages.LOGIN_COMPLETE_OLD_USER)
                },
                onError = {
                    err -> err.printStackTrace()
                }
            )
    }
}
package net.zeta.allonsy.utils

import com.google.android.gms.auth.GoogleAuthException

object Constants {
    const val endpoint: String = "https://allons-y.herokuapp.com/"

    const val registered: String  = "REGISTERED"

    const val identityId: String = "IDENTITY_ID"


    //should be loaded from server
    //providers
    const val GOOGLE = "google"
    const val FACEBOOK = "facebook"
    const val TWITTER = "twitter"

    const val RC_GOOGLE_SIGNIN = 0x01

}
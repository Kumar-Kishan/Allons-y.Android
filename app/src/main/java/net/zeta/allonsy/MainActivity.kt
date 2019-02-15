package net.zeta.allonsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import net.zeta.allonsy.contracts.Actor
import net.zeta.allonsy.utils.Constants
import net.zeta.allonsy.utils.Constants.RC_GOOGLE_SIGNIN
import net.zeta.allonsy.utils.Messages
import org.jetbrains.anko.contentView
import org.jetbrains.anko.email
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        if (savedInstanceState == null) {
            Navigation.findNavController(contentView!!).navigate(R.id.registerFragment)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("Request code %d and google  %d", requestCode, RC_GOOGLE_SIGNIN)
        when(requestCode){
            RC_GOOGLE_SIGNIN -> {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)
                Timber.d("User name %s, User email %s, User Id: %s",  account.displayName ,account.email , account.id)
                viewModel.login(email= account.email!!,providerId= account.id!!,provider= Constants.GOOGLE, name = account.displayName!!, loginListener = object : Actor {
                    override fun message(actor: Actor?, message: Messages) {
                        when(message){
                            Messages.LOGIN_COMPLETE_OLD_USER -> {
                                Navigation.findNavController(contentView!!).navigate(R.id.mainFragment)
                            }
                            Messages.LOGIN_COMPLETE_NEW_USER -> {
                                Navigation.findNavController(contentView!!).navigate(R.id.attributeFragment)
                            }
                        }
                    }
                })

            }
        }
    }
}

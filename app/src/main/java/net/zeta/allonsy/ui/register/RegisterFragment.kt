package net.zeta.allonsy.ui.register

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.register_fragment.*
import net.zeta.allonsy.R
import net.zeta.allonsy.contracts.Actor
import net.zeta.allonsy.utils.Constants.RC_GOOGLE_SIGNIN
import net.zeta.allonsy.utils.Messages
import timber.log.Timber

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        openCamera.setOnClickListener {
            view ->
                Navigation.findNavController(view).navigate(R.id.cameraFragment)
        }


        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        viewModel.registerInfos(application = this.activity!!.application,registrationListener = object : Actor {
            override fun message(actor: Actor?, message: Messages) {
                Timber.d("Got Message as %s", message.toString())
                when(message){
                    Messages.REGISTRATION_COMPLETE -> {
//                        view!!.findNavController().popBackStack()
//                        view!!.findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                        //Need to show sign in or skip for now button
                        signinView.visibility = VISIBLE
                        googleSignInButton.setOnClickListener {
                            activity?.startActivityForResult(GoogleSignIn.getClient(activity as Activity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()).signInIntent, RC_GOOGLE_SIGNIN)
                        }
                        btnSkipRegistration.setOnClickListener{
                            view ->
                                view.findNavController().popBackStack()
                                view.findNavController().navigate(R.id.mainFragment)
                        }
                    }
                    else -> {
                    }
                }
            }
        })
    }

}

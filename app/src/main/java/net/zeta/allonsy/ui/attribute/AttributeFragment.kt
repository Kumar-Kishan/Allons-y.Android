package net.zeta.allonsy.ui.attribute

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.zeta.allonsy.R

class AttributeFragment : Fragment() {

    companion object {
        fun newInstance() = AttributeFragment()
    }

    private lateinit var viewModel: AttributeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.attribute_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AttributeViewModel::class.java)
        // TODO: Use the ViewModel

    }

}

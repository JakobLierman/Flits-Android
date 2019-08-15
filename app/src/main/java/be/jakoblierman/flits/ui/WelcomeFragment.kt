package be.jakoblierman.flits.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import be.jakoblierman.flits.R

class WelcomeFragment : Fragment() {

    private lateinit var registerButton: Button
    private lateinit var signinButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton = view.findViewById(R.id.button_register)
        signinButton = view.findViewById(R.id.button_sign_in)

        // OnClickListeners buttons
        registerButton.setOnClickListener {
            openFragment(RegisterFragment())
        }
        signinButton.setOnClickListener {
            openFragment(LoginFragment())
        }
    }

    private fun openFragment(newFragment: Fragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_content_container, newFragment)
            .addToBackStack(null)
            .commit()
    }

}

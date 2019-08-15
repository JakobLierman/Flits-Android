package be.jakoblierman.flits.ui


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentRegisterBinding
import be.jakoblierman.flits.viewmodels.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.nulabinc.zxcvbn.Zxcvbn
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress


class RegisterFragment : Fragment() {

    private lateinit var registerButton: Button
    private lateinit var fullNameInput: TextInputEditText
    private lateinit var fullNameInputLayout: TextInputLayout
    private lateinit var emailInput: TextInputEditText
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInput: TextInputEditText
    private lateinit var passwordInputLayout: TextInputLayout

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        val binding: FragmentRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton = view.findViewById(R.id.button_register)
        fullNameInput = view.findViewById(R.id.input_fullName)
        fullNameInputLayout = view.findViewById(R.id.inputlayout_fullName)
        emailInput = view.findViewById(R.id.input_email)
        emailInputLayout = view.findViewById(R.id.inputlayout_email)
        passwordInput = view.findViewById(R.id.input_password)
        passwordInputLayout = view.findViewById(R.id.inputlayout_password)

        // OnClickListener register button
        registerButton.setOnClickListener {
            setLoading(true)
            if (viewModel.isValidEmail(emailInput.text.toString())) {
                viewModel.register(
                    fullNameInput.text.toString(),
                    emailInput.text.toString(),
                    passwordInput.text.toString()
                )
                // TODO other activity
            } else {
                emailInputLayout.error = getString(R.string.email_taken)
                emailInputLayout.isErrorEnabled = true
                setLoading(false)
            }
        }

        // TextWatchers
        emailInput.addTextChangedListener(watcher)
        passwordInput.addTextChangedListener(watcher)
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            val nonBlank = !(fullNameInput.text.isNullOrBlank())
            fullNameInputLayout.error = getString(R.string.required)
            fullNameInputLayout.isErrorEnabled = !nonBlank

            val validEmail = isValidEmailAddress(emailInput.text.toString())
            emailInputLayout.error = getString(R.string.invalid_email)
            emailInputLayout.isErrorEnabled = !validEmail

            val validPassword = Zxcvbn().measure(passwordInput.text.toString()).score >= 2
            passwordInputLayout.error = getString(R.string.invalid_password)
            passwordInputLayout.isErrorEnabled = !validPassword

            registerButton.isEnabled = (nonBlank && validEmail && validPassword)
        }

        private fun isValidEmailAddress(email: String): Boolean {
            var result = true
            try {
                val emailAddr = InternetAddress(email)
                emailAddr.validate()
            } catch (ex: AddressException) {
                result = false
            }
            return result
        }
    }

    private fun setLoading(loading: Boolean) {
        registerButton.isEnabled = !loading
        fullNameInput.isEnabled = !loading
        emailInput.isEnabled = !loading
        passwordInput.isEnabled = !loading
        if (loading)
            view!!.findViewById<CardView>(R.id.loading).visibility = View.VISIBLE
        else
            view!!.findViewById<CardView>(R.id.loading).visibility = View.GONE
    }

}

package be.jakoblierman.flits.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentAddSpeedCameraBinding
import be.jakoblierman.flits.model.SpeedCamera
import be.jakoblierman.flits.model.User
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class AddSpeedCameraFragment : Fragment() {

    private lateinit var inputLocation: TextInputEditText
    private lateinit var inputDescription: TextInputEditText
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button
    private lateinit var radioKind: RadioGroup

    companion object {
        @JvmStatic
        fun newInstance() =
            AddSpeedCameraFragment()
    }

    private lateinit var viewModel: SpeedCameraViewModel
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(SpeedCameraViewModel::class.java)


        val binding: FragmentAddSpeedCameraBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_speed_camera, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCancel = view.findViewById(R.id.button_cancel)
        buttonSave = view.findViewById(R.id.button_save)
        inputLocation = view.findViewById(R.id.input_location)
        inputDescription = view.findViewById(R.id.input_description)
        radioKind = view.findViewById(R.id.radio_speedCamera_kind)

        // OnClickListeners buttons
        buttonCancel.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        buttonSave.setOnClickListener {
            val speedCamera = SpeedCamera(
                location = inputLocation.text.toString(),
                description = inputDescription.text.toString(),
                kind = view.findViewById<RadioButton>(radioKind.checkedRadioButtonId).text.toString(),
                user = User(
                    sharedPrefs.getString("ID", "")!!,
                    sharedPrefs.getString("NAME", "")!!,
                    sharedPrefs.getString("EMAIL", "")!!
                )
            )
            viewModel.postSpeedCamera(sharedPrefs.getString("TOKEN", "")!!, speedCamera)
            activity!!.supportFragmentManager.popBackStack()
            Snackbar.make(view, "Saved succesfully", Snackbar.LENGTH_SHORT).show()
        }

        // TextWatchers
        inputLocation.addTextChangedListener(watcher)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_speedcamera)
        sharedPrefs = activity!!.getSharedPreferences("USER_CREDENTIALS", Context.MODE_PRIVATE)
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            buttonSave.isEnabled = !(inputLocation.text.isNullOrBlank())
        }
    }

}

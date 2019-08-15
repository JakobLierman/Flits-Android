package be.jakoblierman.flits.ui


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentAddPoliceCheckBinding
import be.jakoblierman.flits.viewmodels.PoliceCheckViewModel
import com.google.android.material.textfield.TextInputEditText

class AddPoliceCheckFragment : Fragment() {

    private lateinit var inputLocation: TextInputEditText
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button

    companion object {
        @JvmStatic
        fun newInstance() =
            AddPoliceCheckFragment()
    }

    private lateinit var viewModel: PoliceCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(PoliceCheckViewModel::class.java)

        val binding: FragmentAddPoliceCheckBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_police_check, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // OnClickListeners buttons
        buttonCancel = view.findViewById(R.id.button_cancel)
        buttonCancel.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        buttonSave = view.findViewById(R.id.button_save)
        buttonSave.setOnClickListener {
            // TODO - save
        }

        // TextWatchers
        inputLocation = view.findViewById(R.id.input_location)
        inputLocation.addTextChangedListener(watcher)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_policecheck)
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            buttonSave.isEnabled = !(inputLocation.text.isNullOrBlank())
        }
    }

}

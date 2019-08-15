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
import be.jakoblierman.flits.databinding.FragmentAddAvgSpeedCheckBinding
import be.jakoblierman.flits.viewmodels.AvgSpeedCheckViewModel
import com.google.android.material.textfield.TextInputEditText


class AddAvgSpeedCheckFragment : Fragment() {

    private lateinit var inputBeginLocation: TextInputEditText
    private lateinit var inputEndLocation: TextInputEditText
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button

    companion object {
        @JvmStatic
        fun newInstance() =
            AddAvgSpeedCheckFragment()
    }

    private lateinit var viewModel: AvgSpeedCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(AvgSpeedCheckViewModel::class.java)

        val binding: FragmentAddAvgSpeedCheckBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_avg_speed_check, container, false)
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
        inputBeginLocation = view.findViewById(R.id.input_beginlocation)
        inputBeginLocation.addTextChangedListener(watcher)
        inputEndLocation = view.findViewById(R.id.input_endlocation)
        inputEndLocation.addTextChangedListener(watcher)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_avgspeedcheck)
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            buttonSave.isEnabled = !(inputBeginLocation.text.isNullOrBlank() || inputEndLocation.text.isNullOrBlank())
        }
    }

}
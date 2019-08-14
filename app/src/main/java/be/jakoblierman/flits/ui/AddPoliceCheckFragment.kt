package be.jakoblierman.flits.ui


import android.os.Bundle
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

class AddPoliceCheckFragment : Fragment() {

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
        view.findViewById<Button>(R.id.button_cancel).setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
        view.findViewById<Button>(R.id.button_save).setOnClickListener {
            // TODO - save
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_policecheck)
    }

}

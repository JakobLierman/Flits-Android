package be.jakoblierman.flits.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentPoliceCheckBinding
import be.jakoblierman.flits.viewmodels.PoliceCheckViewModel
import kotlinx.android.synthetic.main.fragment_police_check.*

private const val ARG_POLICECHECK_ID = "policeCheckId"

class PoliceCheckFragment : Fragment() {

    private var policeCheckId: String? = null

    companion object {
        @JvmStatic
        fun newInstance(policeCheckId: String) =
            PoliceCheckFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_POLICECHECK_ID, policeCheckId)
                }
            }
    }

    private lateinit var viewModel: PoliceCheckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            policeCheckId = it.getString(ARG_POLICECHECK_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(PoliceCheckViewModel::class.java)

        val binding: FragmentPoliceCheckBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_police_check, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString("policeCheckId")?.let { viewModel.getPoliceCheckById(it) }

        viewModel.policeCheck.removeObservers(this)
        viewModel.policeCheck.observe(this, Observer { policeCheck ->
            // Hides description elements if no description is present
            if (policeCheck.description.isBlank()) {
                text_description_title.visibility = View.GONE
                text_description_value.visibility = View.GONE
            }

            // Hides image is none is present
            if (policeCheck.imagePath.isBlank())
                imageView.visibility = View.GONE
        })
    }

}

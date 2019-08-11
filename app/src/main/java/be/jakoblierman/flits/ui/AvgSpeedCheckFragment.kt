package be.jakoblierman.flits.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentAvgSpeedCheckBinding
import be.jakoblierman.flits.viewmodels.AvgSpeedCheckViewModel

private const val ARG_AVGSPEEDCHECK_ID = "avgSpeedCheckId"

class AvgSpeedCheckFragment : Fragment() {

    private var avgSpeedCheckId: String? = null

    companion object {
        @JvmStatic
        fun newInstance(avgSpeedCheckId: String) =
            AvgSpeedCheckFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_AVGSPEEDCHECK_ID, avgSpeedCheckId)
                }
            }
    }

    private lateinit var viewModel: AvgSpeedCheckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            avgSpeedCheckId = it.getString(ARG_AVGSPEEDCHECK_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(AvgSpeedCheckViewModel::class.java)

        val binding: FragmentAvgSpeedCheckBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_avg_speed_check, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString("avgSpeedCheckId")?.let { viewModel.getAvgSpeedCheckById(it) }
    }

}

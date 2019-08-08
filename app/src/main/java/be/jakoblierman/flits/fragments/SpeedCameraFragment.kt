package be.jakoblierman.flits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentSpeedCameraBinding
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel


class SpeedCameraFragment : Fragment() {

    companion object {
        fun newInstance() = SpeedCameraFragment()
    }

    private lateinit var viewModel: SpeedCameraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(SpeedCameraViewModel::class.java)

        val binding: FragmentSpeedCameraBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_speed_camera, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }

}

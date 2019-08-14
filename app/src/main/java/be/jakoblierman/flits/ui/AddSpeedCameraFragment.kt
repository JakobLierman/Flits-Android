package be.jakoblierman.flits.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentAddSpeedCameraBinding
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel

class AddSpeedCameraFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            AddSpeedCameraFragment()
    }

    private lateinit var viewModel: SpeedCameraViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_speedcamera)
    }

}

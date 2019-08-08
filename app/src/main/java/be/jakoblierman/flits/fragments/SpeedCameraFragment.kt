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
import kotlinx.android.synthetic.main.fragment_speed_camera.*


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Hides description elements if no description is present
        if (viewModel.speedCamera.description.isBlank()) {
            text_description_title.visibility = View.GONE
            text_description_value.visibility = View.GONE
        }

        // Hides image is none is present
        if (viewModel.speedCamera.imagePath.isBlank())
            imageView.visibility = View.GONE
        
    }

}

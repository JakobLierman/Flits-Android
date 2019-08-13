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
import be.jakoblierman.flits.databinding.FragmentSpeedCameraBinding
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel
import kotlinx.android.synthetic.main.fragment_speed_camera.*

private const val ARG_SPEEDCAMERA_ID = "speedCameraId"

class SpeedCameraFragment : Fragment() {

    private var speedCameraId: String? = null

    companion object {
        @JvmStatic
        fun newInstance(speedCameraId: String?) =
            SpeedCameraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SPEEDCAMERA_ID, speedCameraId)
                }
            }
    }

    private lateinit var viewModel: SpeedCameraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            speedCameraId = it.getString(ARG_SPEEDCAMERA_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(SpeedCameraViewModel::class.java)

        val binding: FragmentSpeedCameraBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_speed_camera, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString("speedCameraId")?.let { viewModel.getSpeedCameraById(it) }

        viewModel.speedCamera.removeObservers(this)
        viewModel.speedCamera.observe(this, Observer { speedCamera ->
            // Hides description elements if no description is present
            if (speedCamera.description.isBlank()) {
                text_description_title.visibility = View.GONE
                text_description_value.visibility = View.GONE
            }

            // Hides image is none is present
            if (speedCamera.imagePath.isBlank())
                imageView.visibility = View.GONE
        })
    }

}

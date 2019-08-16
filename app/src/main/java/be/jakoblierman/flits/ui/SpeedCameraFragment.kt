package be.jakoblierman.flits.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import be.jakoblierman.flits.R
import be.jakoblierman.flits.databinding.FragmentSpeedCameraBinding
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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
    private lateinit var sharedPrefs: SharedPreferences

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_delete)
        fab.setOnClickListener { fabView ->
            viewModel.deleteSpeedCamera(sharedPrefs.getString("TOKEN", "")!!)
            activity!!.supportFragmentManager.popBackStack()
            Snackbar.make(fabView, getString(R.string.deleting), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.menu_speedCamera)
        sharedPrefs = activity!!.getSharedPreferences("USER_CREDENTIALS", Context.MODE_PRIVATE)

        arguments?.getString("speedCameraId")?.let { viewModel.getSpeedCameraById(it) }
        viewModel.speedCamera.removeObservers(this)
        viewModel.speedCamera.observe(this, Observer { speedCamera ->
            // Shows description elements if one is present
            if (!speedCamera.description.isBlank()) {
                text_description_title.visibility = View.VISIBLE
                text_description_value.visibility = View.VISIBLE
            }

            // Shows image if one is present
            if (!speedCamera.imagePath.isBlank())
                imageView.visibility = View.VISIBLE

            // Shows delete button if item owned by logged in user
            val fab: FloatingActionButton = view!!.findViewById(R.id.fab_delete)
            if (viewModel.speedCamera.value!!.user.id == sharedPrefs.getString("ID", "")!!)
                fab.show()
        })
    }

}

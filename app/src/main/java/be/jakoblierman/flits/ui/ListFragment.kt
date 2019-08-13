package be.jakoblierman.flits.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.jakoblierman.flits.R
import be.jakoblierman.flits.adapters.AvgSpeedCheckRecyclerViewAdapter
import be.jakoblierman.flits.adapters.PoliceCheckRecyclerViewAdapter
import be.jakoblierman.flits.adapters.SpeedCameraRecyclerViewAdapter
import be.jakoblierman.flits.viewmodels.AvgSpeedCheckViewModel
import be.jakoblierman.flits.viewmodels.PoliceCheckViewModel
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel

const val ARG_ITEM_KIND_ID = "itemKindId"

class ListFragment : Fragment() {

    private val thisFragment = this
    private var itemKindId = R.id.nav_speedCamera

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var speedCameraViewModel: SpeedCameraViewModel
    private lateinit var avgSpeedCheckViewModel: AvgSpeedCheckViewModel
    private lateinit var policeCheckViewModel: PoliceCheckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            itemKindId = it.getInt(ARG_ITEM_KIND_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Set the adapter and loads in data
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                when (itemKindId) {
                    R.id.nav_speedCamera -> {
                        adapter = SpeedCameraRecyclerViewAdapter(listener)
                        speedCameraViewModel = ViewModelProviders.of(thisFragment).get(SpeedCameraViewModel::class.java)
                        speedCameraViewModel.speedCameras.removeObservers(thisFragment)
                        speedCameraViewModel.speedCameras.observe(thisFragment, Observer {
                            (adapter as SpeedCameraRecyclerViewAdapter).setData(it)
                        })
                    }
                    R.id.nav_avgSpeedCheck -> {
                        adapter = AvgSpeedCheckRecyclerViewAdapter(listener)
                        avgSpeedCheckViewModel =
                            ViewModelProviders.of(thisFragment).get(AvgSpeedCheckViewModel::class.java)
                        avgSpeedCheckViewModel.avgSpeedChecks.removeObservers(thisFragment)
                        avgSpeedCheckViewModel.avgSpeedChecks.observe(thisFragment, Observer {
                            (adapter as AvgSpeedCheckRecyclerViewAdapter).setData(it)
                        })
                    }
                    R.id.nav_policeCheck -> {
                        adapter = PoliceCheckRecyclerViewAdapter(listener)
                        policeCheckViewModel = ViewModelProviders.of(thisFragment).get(PoliceCheckViewModel::class.java)
                        policeCheckViewModel.policeChecks.removeObservers(thisFragment)
                        policeCheckViewModel.policeChecks.observe(thisFragment, Observer {
                            (adapter as PoliceCheckRecyclerViewAdapter).setData(it)
                        })
                    }
                }
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(itemKindId: Int, itemId: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(itemKindId: Int) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_KIND_ID, itemKindId)
                }
            }
    }

}
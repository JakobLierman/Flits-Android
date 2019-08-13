package be.jakoblierman.flits.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.jakoblierman.flits.R
import be.jakoblierman.flits.adapters.AvgSpeedCheckRecyclerViewAdapter
import be.jakoblierman.flits.adapters.PoliceCheckRecyclerViewAdapter
import be.jakoblierman.flits.adapters.SpeedCameraRecyclerViewAdapter
import be.jakoblierman.flits.model.AvgSpeedCheck
import be.jakoblierman.flits.model.PoliceCheck
import be.jakoblierman.flits.model.SpeedCamera

const val ARG_ITEM_KIND_ID = "itemKindId"

class ListFragment : Fragment() {

    private var itemKindId = R.id.nav_speedCamera

    private var listener: OnListFragmentInteractionListener? = null
    // TODO - Items
    private var speedCameras = listOf<SpeedCamera>()
    private var avgSpeedChecks = listOf<AvgSpeedCheck>()
    private var policeChecks = listOf<PoliceCheck>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            itemKindId = it.getInt(ARG_ITEM_KIND_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                when (itemKindId) {
                    R.id.nav_speedCamera ->
                        adapter = SpeedCameraRecyclerViewAdapter(speedCameras, listener)
                    R.id.nav_avgSpeedCheck ->
                        adapter = AvgSpeedCheckRecyclerViewAdapter(avgSpeedChecks, listener)
                    R.id.nav_policeCheck ->
                        adapter = PoliceCheckRecyclerViewAdapter(policeChecks, listener)
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
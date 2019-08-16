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
import be.jakoblierman.flits.databinding.FragmentAvgSpeedCheckBinding
import be.jakoblierman.flits.viewmodels.AvgSpeedCheckViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

private const val ARG_AVGSPEEDCHECK_ID = "avgSpeedCheckId"

class AvgSpeedCheckFragment : Fragment() {

    private var avgSpeedCheckId: String? = null

    companion object {
        @JvmStatic
        fun newInstance(avgSpeedCheckId: String?) =
            AvgSpeedCheckFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_AVGSPEEDCHECK_ID, avgSpeedCheckId)
                }
            }
    }

    private lateinit var viewModel: AvgSpeedCheckViewModel
    private lateinit var sharedPrefs: SharedPreferences

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_delete)
        fab.setOnClickListener { fabView ->
            viewModel.deleteAvgSpeedCheck(sharedPrefs.getString("TOKEN", "")!!)
            activity!!.supportFragmentManager.popBackStack()
            Snackbar.make(fabView, getString(R.string.deleting), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.menu_avgSpeedCheck)
        sharedPrefs = activity!!.getSharedPreferences("USER_CREDENTIALS", Context.MODE_PRIVATE)

        arguments?.getString("avgSpeedCheckId")?.let { viewModel.getAvgSpeedCheckById(it) }
        viewModel.avgSpeedCheck.removeObservers(this)
        viewModel.avgSpeedCheck.observe(this, Observer { avgSpeedCheck ->
            // Shows delete button if item owned by logged in user
            val fab: FloatingActionButton = view!!.findViewById(R.id.fab_delete)
            if (viewModel.avgSpeedCheck.value!!.user.id == sharedPrefs.getString("ID", "")!!)
                fab.show()
        })
    }

}

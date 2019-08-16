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
import be.jakoblierman.flits.databinding.FragmentPoliceCheckBinding
import be.jakoblierman.flits.viewmodels.PoliceCheckViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_police_check.*

private const val ARG_POLICECHECK_ID = "policeCheckId"

class PoliceCheckFragment : Fragment() {

    private var policeCheckId: String? = null

    companion object {
        @JvmStatic
        fun newInstance(policeCheckId: String?) =
            PoliceCheckFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_POLICECHECK_ID, policeCheckId)
                }
            }
    }

    private lateinit var viewModel: PoliceCheckViewModel
    private lateinit var sharedPrefs: SharedPreferences

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_delete)
        fab.setOnClickListener { fabView ->
            viewModel.deletePoliceCheck(sharedPrefs.getString("TOKEN", "")!!)
            activity!!.supportFragmentManager.popBackStack()
            Snackbar.make(fabView, getString(R.string.deleting), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.menu_policeCheck)
        sharedPrefs = activity!!.getSharedPreferences("USER_CREDENTIALS", Context.MODE_PRIVATE)

        arguments?.getString("policeCheckId")?.let { viewModel.getPoliceCheckById(it) }
        viewModel.policeCheck.removeObservers(this)
        viewModel.policeCheck.observe(this, Observer { policeCheck ->
            // Shows description elements if one is present
            if (!policeCheck.description.isBlank()) {
                text_description_title.visibility = View.VISIBLE
                text_description_value.visibility = View.VISIBLE
            }

            // Shows image if one is present
            if (!policeCheck.imagePath.isBlank())
                imageView.visibility = View.VISIBLE

            // Shows delete button if item owned by logged in user
            val fab: FloatingActionButton = view!!.findViewById(R.id.fab_delete)
            if (viewModel.policeCheck.value!!.user.id == sharedPrefs.getString("ID", "")!!)
                fab.show()
        })
    }

}

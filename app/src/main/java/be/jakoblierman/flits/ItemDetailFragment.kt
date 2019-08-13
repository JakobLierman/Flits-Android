package be.jakoblierman.flits


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val ARG_ITEMKIND_ID = "itemKindId"
private const val ARG_ITEMID = "itemId"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ItemDetailFragment : Fragment() {
    private var itemKindId: Int? = null
    private var itemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemKindId = it.getInt(ARG_ITEMKIND_ID)
            itemId = it.getString(ARG_ITEMID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }


    companion object {
        /**
         * Creates a new instance of this fragment using the provided parameters.
         *
         * @param itemKindId Parameter 1.
         * @param itemId Parameter 2.
         * @return A new instance of fragment ItemDetailFragment.
         */
        @JvmStatic
        fun newInstance(itemKindId: Int, itemId: String) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ITEMKIND_ID, itemKindId)
                    putString(ARG_ITEMID, itemId)
                }
            }
    }
}

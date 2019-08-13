package be.jakoblierman.flits.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import be.jakoblierman.flits.R
import be.jakoblierman.flits.model.AvgSpeedCheck
import be.jakoblierman.flits.ui.ListFragment
import kotlinx.android.synthetic.main.fragment_listitem.view.*

class AvgSpeedCheckRecyclerViewAdapter(
    private val mListener: ListFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<AvgSpeedCheckRecyclerViewAdapter.ViewHolder>() {

    private var mValues = mutableListOf<AvgSpeedCheck>()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as AvgSpeedCheck
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(R.id.nav_avgSpeedCheck, item.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.titleView.text = item.beginLocation
        holder.subtitleView.text = item.timeCreated.toString()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titleView: TextView = mView.item_title
        val subtitleView: TextView = mView.item_subtitle
    }

    fun setData(newData: List<AvgSpeedCheck>) {
        val postDiffCallback = PostDiffCallback(mValues, newData)
        val diffResult = DiffUtil.calculateDiff(postDiffCallback)

        mValues.clear()
        mValues.addAll(newData)
        
        diffResult.dispatchUpdatesTo(this)
    }

    internal inner class PostDiffCallback(
        private val oldItems: List<AvgSpeedCheck>,
        private val newItems: List<AvgSpeedCheck>
    ) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id === newItems[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}
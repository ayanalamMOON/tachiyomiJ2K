package eu.kanade.tachiyomi.ui.source.browse

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.loadingindicator.LoadingIndicator
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import eu.kanade.tachiyomi.R

class ProgressItem : AbstractFlexibleItem<ProgressItem.Holder>() {
    private var loadMore = true

    override fun getLayoutRes(): Int = R.layout.source_progress_item

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
    ): Holder = Holder(view, adapter)

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: Holder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        holder.progressBar.visibility = View.GONE
        holder.progressMessage.visibility = View.GONE

        if (!adapter.isEndlessScrollEnabled) {
            loadMore = false
        }

        if (loadMore) {
            holder.progressBar.visibility = View.VISIBLE
        } else {
            holder.progressMessage.visibility = View.VISIBLE
        }
    }

    override fun equals(other: Any?): Boolean = this === other

    class Holder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
    ) : FlexibleViewHolder(view, adapter) {
        val progressBar: LoadingIndicator = view.findViewById(R.id.progress_bar)
        val progressMessage: TextView = view.findViewById(R.id.progress_message)
    }
}

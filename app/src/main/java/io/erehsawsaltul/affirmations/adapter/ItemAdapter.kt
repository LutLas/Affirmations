package io.erehsawsaltul.affirmations.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.erehsawsaltul.affirmations.R
import io.erehsawsaltul.affirmations.model.Hymns

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val dataSet: List<Hymns>
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Hymns object.
    class ItemViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        val textViewNo: TextView = view.findViewById(R.id.item_no)
        val textViewTitle: TextView = view.findViewById(R.id.item_title)
        val textViewAuthor: TextView = view.findViewById(R.id.item_author)
        //val textViewTitleStringer = ""
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.textViewNo.text = item.no
        //holder.textViewTitle.text = item.title.values.toString()
        holder.textViewTitle.text = TextUtils.join("\n",item.title.values)
        holder.textViewAuthor.text = TextUtils.join("\n",item.author.values)
        /*for (titled in item.title.values){
            holder.textViewTitle.text = holder.textViewTitleStringer.plus("$titled\n")
        }*/
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int {
        return dataSet.size
    }
}
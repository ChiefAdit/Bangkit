package com.example.try1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.try1.R
import com.example.try1.Retrofit.Data.Content
import android.widget.Filter
import android.widget.Filterable
import java.util.Locale

class ContentAdapter(private var contentList: List<Content>) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>(), Filterable {

    private var onItemClickListener: ((Content) -> Unit)? = null
    private var filteredContentList: List<Content> = contentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return ContentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val currentItem = contentList[position]
        holder.titleTextView.text = currentItem.title
        holder.shortDescriptionTextView.text = currentItem.short_description

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(currentItem)
        }
    }

    fun setOnItemClickListener(listener: (Content) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return filteredContentList.size
    }

    fun setData(newContentList: List<Content>) {
        contentList = newContentList
        filteredContentList = newContentList
        notifyDataSetChanged()
    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val shortDescriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault()) ?: ""
                val filteredList = if (query.isEmpty()) {
                    contentList
                } else {
                    contentList.filter { content ->
                        content.title.lowercase(Locale.getDefault()).contains(query)
                    }.toMutableList()
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredContentList = results?.values as? MutableList<Content> ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }

}
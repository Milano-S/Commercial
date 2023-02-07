package com.exclr8.n1corporate.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.R

class SearchAdapter(val productSpecification: List<ProductSpecificationModel?>, val callback: (productSpecificationModel: ProductSpecificationModel) -> Unit) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView

        init {
            title = itemView.findViewById<View>(R.id.search_title) as TextView
            itemView.setOnClickListener { v ->
                callback(productSpecification[adapterPosition]!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_searchlist, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = productSpecification[position]!!.SpecDescription
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return productSpecification.size
    }

}
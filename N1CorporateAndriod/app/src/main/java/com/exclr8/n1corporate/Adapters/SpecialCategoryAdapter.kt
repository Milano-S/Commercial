package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.AsyncTasks.SpecialCategoryModel
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardSpecialCategoryBinding

class SpecialCategoryAdapter(val Dataset: List<SpecialCategoryModel?>, val context: Context, val callback: (ProductCategoryId: Int) -> Unit) : RecyclerView.Adapter<SpecialCategoryAdapter.ViewHolder>() {
    var screenWidth = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardSpecialCategoryBinding = CardSpecialCategoryBinding.bind(itemView)

        init {
            val displayMetrics = context.resources.displayMetrics
            screenWidth = displayMetrics.widthPixels

            itemView.setOnClickListener { v ->
                callback(Dataset[adapterPosition]!!.id)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_special_category, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtvSpecialTitle.text = Dataset[position]!!.title

        when (Dataset[position]!!.id) {
            1 -> {
                val id = context.resources.getIdentifier(Dataset[position]!!.image, "drawable", context.packageName)
                holder.binding.imageSpecialStar.setImageResource(id)
                holder.binding.cardSpecialQuantity.visible()
                holder.binding.txtvSpecialQuantity.text = Dataset[position]!!.data!!.count().toString()
            }
            2 -> {
                val id = context.resources.getIdentifier(Dataset[position]!!.image, "drawable", context.packageName)
                holder.binding.imageSpecialStar.setImageResource(id)
                holder.binding.cardSpecialQuantity.visible()
                holder.binding.txtvSpecialQuantity.text = Dataset[position]!!.data!!.count().toString()
            }
            3 -> {
                val id = context.resources.getIdentifier(Dataset[position]!!.image, "drawable", context.packageName)
                holder.binding.imageSpecialStar.setImageResource(id)
                holder.binding.cardSpecialQuantity.visible()
                holder.binding.txtvSpecialQuantity.text = Dataset[position]!!.data!!.count().toString()
            }
            4 -> {
                val id = context.resources.getIdentifier(Dataset[position]!!.image, "drawable", context.packageName)
                holder.binding.imageSpecialStar.setImageResource(id)
                holder.binding.cardSpecialQuantity.visible()
                holder.binding.txtvSpecialQuantity.text = Dataset[position]!!.data!!.count().toString()
            }
        }

        if (Helper().isTablet(context)) {
            val columnCount = screenWidth / 650
            if (columnCount > 1) {
                holder.binding.cardSpecial.layoutParams.width = screenWidth / columnCount - 30 * columnCount
                holder.binding.cardSpecial.layoutParams.height = (screenWidth / columnCount - 30 * columnCount) / 4
            } else {
                holder.binding.cardSpecial.layoutParams.width = screenWidth - 20
                holder.binding.cardSpecial.layoutParams.height = (screenWidth - 20) / 4
            }
        } else {
            holder.binding.cardSpecial.layoutParams.width = screenWidth - 10
            holder.binding.cardSpecial.layoutParams.height = (screenWidth - 10) / 4
        }
    }

    override fun getItemCount(): Int {
        return Dataset.size
    }
}
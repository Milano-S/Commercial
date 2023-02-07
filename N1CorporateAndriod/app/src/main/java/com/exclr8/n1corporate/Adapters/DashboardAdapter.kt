package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.DataModels.GenericModels.MenuOptionsModel
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.R

class DashboardAdapter(val list: MutableList<MenuOptionsModel>, val context: Context, val callback: (MenuOptionsId: Int) -> Unit) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    var screenWidth: Int = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView
        var title: TextView



        init {
            icon = itemView.findViewById<View>(R.id.imageCardDashboardIcon) as ImageView
            title = itemView.findViewById<View>(R.id.txtvCardDashboardTitle) as TextView
            itemView.setOnClickListener {
                callback(list[adapterPosition].id)
            }

            Helper().getScreenSize(context){ Width, Height ->
                screenWidth = Width
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_dashboard_item, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.icon.setImageResource(list[position].imageURL)
        holder.title.text = list[position].title

        if (Helper().isTablet(context)) {
            val columnCount = screenWidth.div(350)
            holder.icon.layoutParams.height = (screenWidth / columnCount) - 15 * columnCount
            holder.icon.layoutParams.width = (screenWidth / columnCount) - 15 * columnCount
        } else {
            if (screenWidth.div(3) > 200) {
                holder.icon.layoutParams.height = (screenWidth / 3) - 120
                holder.icon.layoutParams.width = (screenWidth / 3) - 120
            } else {
                holder.icon.layoutParams.height = screenWidth - 80
                holder.icon.layoutParams.width = screenWidth - 80
            }
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }
}
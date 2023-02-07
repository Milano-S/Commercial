package com.exclr8.n1corporate.Fragments

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.Helpers.CountDrawable
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.R

open class CartNavFragment(layout: Int) : Fragment(layout) {
        lateinit var defaultMenu: Menu

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.cart_menu, menu)
        defaultMenu = menu
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val order: List<OrderItemModel?> = PreferenceHelper().getOrderList()
        try {
            if (!order.isEmpty()) {
                setCount(context, order.size.toString())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

        fun setCount(context: Context?, count: String?) {
            val menuItem = defaultMenu.findItem(R.id.action_order)
            val icon = menuItem.icon as LayerDrawable
            val badge: CountDrawable

            // Reuse drawable if possible
            val reuse = icon.findDrawableByLayerId(R.id.ic_group_count)
            badge = if (reuse != null && reuse is CountDrawable) {
                reuse
            } else {
                CountDrawable(context)
            }
            badge.setCount(count)
            icon.mutate()
            icon.setDrawableByLayerId(R.id.ic_group_count, badge)
        }

}
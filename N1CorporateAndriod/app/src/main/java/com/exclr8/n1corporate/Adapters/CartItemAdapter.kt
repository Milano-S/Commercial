package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.ImageLoader
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardCartItemBinding

class CartItemAdapter(val products: MutableList<ProductSpecificationModel?>, val productsCart: List<OrderItemModel?>, val context: Context, var callback: (ProductSpecificationItem: ProductSpecificationModel?, CartItem: OrderItemModel?, adapterPosition: Int?) -> Unit) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {
    lateinit var view: View

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardCartItemBinding = CardCartItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener { v -> view = v
                callback(products[adapterPosition]!!, productsCart[adapterPosition]!!, 0)
            }

            binding.cardCartSpecRemove.setOnClickListener {
                callback(null, null, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_cart_item, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]!!
        ImageLoader().get(context, product.ImageKey!!, holder.binding.imageCartSpec)

        if (product.HasSponsor!!) {
            holder.binding.cartSponsorImage.visible()
            ImageLoader().get(context, product.SponsorImageKey!!, holder.binding.cartSponsorImage)
        }else {
            holder.binding.cartSponsorImage.gone()
        }

        if(product.IsLastOrdered != null) {
            if (product.IsLastOrdered!!) {
                holder.binding.txtvCartSpecLastOrderDate.text = product.LastOrderDate
                holder.binding.txtvCartSpecLastOrderDate.visible()
            } else {
                holder.binding.txtvCartSpecLastOrderDate.gone()
                holder.binding.txtvCartSpecLastOrderDate.gone()
            }
        } else {
            holder.binding.txtvCartSpecLastOrderDate.gone()
            holder.binding.txtvCartSpecLastOrderDate.gone()
        }

        if(product.IsNew!!){
            holder.binding.txtvCartSpecTitle.setPadding(5,0,0,0)
            holder.binding.imageCartSpecStar.visible()
            val id = context.resources.getIdentifier("star_new", "drawable", context.packageName)
            holder.binding.imageCartSpecStar.setImageResource(id)
        }else if (product.OrderFrequencyCount!! > 0){
            holder.binding.txtvCartSpecTitle.setPadding(5,0,0,0)
            holder.binding.imageCartSpecStar.visible()
            val id = context.resources.getIdentifier("star_freq", "drawable", context.packageName)
            holder.binding.imageCartSpecStar.setImageResource(id)
        }else{
            holder.binding.txtvCartSpecTitle.setPadding(20,0,0,0)
            holder.binding.imageCartSpecStar.gone()
        }

        holder.binding.txtvCartSpecTitle.text = product.SpecDescription
        holder.binding.txtvCartSpecUOM.text = product.OrderUnitType
        holder.binding.txtvCartSpecConfig.text = product.SpecConfiguration
        holder.binding.txtvCartSpecQuantity.text = productsCart[position]?.quantity.toString()
        holder.binding.txtvCartSpecFullSalesPrice.text = productsCart[position]?.quantity?.times(product.FullSalesPrice!!)?.let { Helper().priceFormatter(it) }
    }

    override fun getItemCount(): Int {
        return productsCart.size
    }
}
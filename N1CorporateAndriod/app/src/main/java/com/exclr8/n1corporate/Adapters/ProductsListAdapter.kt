package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.Helpers.*
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardProductlistBinding


class ProductsListAdapter(val productSpecification: List<ProductSpecificationModel?>, val home: Boolean, val context: Context) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    var screenWidth: Int? = null
    val cartitems: MutableList<OrderItemModel> = PreferenceHelper().getOrderList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardProductlistBinding = CardProductlistBinding.bind(itemView)

        init {
            Helper().getScreenSize(context){ Width, Height ->
                screenWidth = Width
            }

            itemView.setOnClickListener { v ->
                val bundle = Bundle()
                bundle.putInt("SalesItemRevisionId", productSpecification[adapterPosition]!!.SalesItemRevisionId!!)
                Helper.productListScrollPosition = adapterPosition
                if (!home) {
                    Navigation.findNavController(v).navigate(R.id.action_nav_productList_to_nav_product_spec, bundle)
                } else {
                    Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_product_spec, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_productlist, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productSpecification[position]!!

        ImageLoader().get(context, product.ImageKey!!, holder.binding.imageProductListSpec)

        var cartItem = cartitems.filter { s -> s.salesItemRevisionId == product.SalesItemRevisionId}

        if (cartItem.size > 0){
            holder.binding.imageProductListSpec.toGrayscale()
            holder.binding.txtvProductListCartQuantity.visible()
            holder.binding.txtvProductListCartQuantity.text = cartItem[0].quantity.toString()
        }else{
            holder.binding.imageProductListSpec.removeGrayscale()
            holder.binding.txtvProductListCartQuantity.gone()
        }

        if (product.IsLastOrdered == true){
            holder.binding.txtvProductListLastOrderDate.text = product.LastOrderDate
            holder.binding.txtvProductListLastOrderDate.visible()
            holder.binding.txtvProductListLastOrderQuantity.text = product.LastOrderQuantity.toString()
            holder.binding.txtvProductListLastOrderQuantity.visible()
        }else {
            holder.binding.txtvProductListLastOrderDate.gone()
            holder.binding.txtvProductListLastOrderQuantity.gone()
        }

        if (product.DiscountedPrice!! > 0){
            holder.binding.imageProductListPromo.visible()
            holder.binding.txtvProductListDiscountedPrice.visible()
            Helper().strikeTextView(holder.binding.txtvProductListDiscountedPrice, product.DiscountedPrice!!)
        }else{
            holder.binding.txtvProductListDiscountedPrice.gone()
            holder.binding.imageProductListPromo.gone()
        }

        if (product.IsNew!!){
            holder.binding.imageProductListSpecial.visible()
            val id = context.resources.getIdentifier("star_new", "drawable", context.packageName)
            holder.binding.imageProductListSpecial.setImageResource(id)
        }else if (product.OrderFrequencyCount!! > 0){
            holder.binding.imageProductListSpecial.visible()
            val id = context.resources.getIdentifier("star_freq", "drawable", context.packageName)
            holder.binding.imageProductListSpecial.setImageResource(id)
        }else{
            holder.binding.imageProductListSpecial.gone()
        }

        if (productSpecification[position]!!.HasSponsor!!) {
            holder.binding.imageProductListSponsor.visible()
            ImageLoader().get(context, product.SponsorImageKey!!, holder.binding.imageProductListSponsor)
        }else{
            holder.binding.imageProductListSponsor.gone()
        }

        holder.binding.txtvProductListTitle.text = product.SpecDescription
        holder.binding.txtvProductListConfig.text = product.SpecConfiguration
        holder.binding.txtvProductListAdditionalInfo.text = product.AddedInformation
        holder.binding.txtvProductListPrice.setText(Helper().priceFormatter(product.FullSalesPrice!!))

        if (Helper().isTablet(context)) {
            val columnCount = screenWidth?.div(350)
            holder.binding.imageProductListSpec.layoutParams.height = (screenWidth!! / columnCount!!) - 15 * columnCount
            holder.binding.imageProductListSpec.layoutParams.width = (screenWidth!! / columnCount) - 15 * columnCount
        } else {
            if (screenWidth?.div(2)!! > 200) {
                holder.binding.imageProductListSpec.layoutParams.height = screenWidth!! / 2 - 80
                holder.binding.imageProductListSpec.layoutParams.width = screenWidth!! / 2 - 80
            } else {
                holder.binding.imageProductListSpec.layoutParams.height = screenWidth!! - 40
                holder.binding.imageProductListSpec.layoutParams.width = screenWidth!! - 40
            }
        }
    }

    // Return the size of your dataset (invoked by the toGrayscalelayout manager)
    override fun getItemCount(): Int {
        return productSpecification.size
    }

    fun ImageView.toGrayscale(){
        val matrix = ColorMatrix().apply {
            setSaturation(0f)
        }
        colorFilter = ColorMatrixColorFilter(matrix)
    }

    fun ImageView.removeGrayscale(){
        val matrix = ColorMatrix().apply {
            setSaturation(1f)
        }
        colorFilter = ColorMatrixColorFilter(matrix)
    }
}
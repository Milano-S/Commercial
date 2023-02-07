package com.exclr8.n1corporate.Fragments.Main.CreateOrder

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import com.exclr8.n1corporate.AsyncTasks.ProductSpecificationAsync
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentProductSpecBinding
import com.exclr8.n1corporate.Fragments.CartNavFragment
import com.exclr8.n1corporate.Helpers.*
import com.exclr8.n1corporate.databinding.FragmentProductSpecNewBinding

class ProductSpecificationFragment : CartNavFragment(R.layout.fragment_product_spec) {
    private lateinit var binding: FragmentProductSpecNewBinding
    lateinit var rootView: View

    var product: ProductSpecificationModel? = null
    var quantity = 1
    var specPrice = 0.0
    var specImage_height = 0f
    val cartitems: MutableList<OrderItemModel> = PreferenceHelper().getOrderList()
    var toggleAdditionalInfo = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductSpecNewBinding.inflate(inflater)
        rootView = binding.root
        Helper().hideKeyboard(requireActivity())

        setHasOptionsMenu(true)
        val displayMetrics = requireContext().resources.displayMetrics
        specImage_height = displayMetrics.widthPixels.toFloat()

        binding.spaceImageHeight.layoutParams.height = displayMetrics.widthPixels - 30


        ProductSpecificationAsync(requireContext(),requireArguments().getInt("SalesItemRevisionId"), requireActivity()).get { Success, ItemSpec ->
            if(Success){
                for (item in cartitems){
                    if (item.salesItemRevisionId == ItemSpec.SalesItemRevisionId){
                        quantity = item.quantity
                        binding.edtxtProductCutSpec.setText(item.cutSpec)
                        binding.edtxtProductPackSpec.setText(item.packSpec)
                        binding.btnProductAddToCart.text = "Update"
                    }
                }
                product = ItemSpec
                showProductDetails()
            }
        }

        binding.btnProductAddToCart.setOnClickListener { v ->
            if (quantity <= 0) {
                Toast.makeText(requireContext(), "Invalid Quantity", Toast.LENGTH_SHORT).show()
            } else {
                var salesItemRevId: Int
                salesItemRevId = product!!.SalesItemRevisionId!!

                val item = OrderItemModel(salesItemRevId, quantity,binding.edtxtProductCutSpec.text.toString(),binding.edtxtProductPackSpec.text.toString())
                PreferenceHelper().addItem(item)
                Navigation.findNavController(rootView).popBackStack()
            }
        }

        binding.btnProductMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
            }
            binding.edtxtProductQTY.setText(quantity.toString())
            binding.txtvProductTotalSalesPrice.setText(Helper().priceFormatter(specPrice * quantity))
        }

        binding.btnProductPlus.setOnClickListener {
            quantity++
            binding.edtxtProductQTY.setText(quantity.toString())
            binding.txtvProductTotalSalesPrice.setText(Helper().priceFormatter(specPrice * quantity))
        }

        binding.edtxtProductQTY.addTextChangedListener {
            if(binding.edtxtProductQTY.text.toString().count() < 6) {
                quantity = if (binding.edtxtProductQTY.text.toString() != "") {
                    binding.edtxtProductQTY.text.toString().toInt()
                } else {
                    0
                }
                binding.txtvProductTotalSalesPrice.setText(Helper().priceFormatter(specPrice * quantity))
            }
        }

        binding.btnProductShowMoreHide.setOnClickListener {
            if (toggleAdditionalInfo) {
                binding.btnProductShowMoreHide.text = "Hide"
                binding.txtvProductAdditionalInfo.maxLines = Int.MAX_VALUE
            }else{
                binding.btnProductShowMoreHide.text = "Show more"
                binding.txtvProductAdditionalInfo.maxLines = 3
            }
            toggleAdditionalInfo = !toggleAdditionalInfo
        }

        return rootView
    }


    fun showProductDetails() {
        ImageLoader().get(requireContext(), product!!.ImageKey!!, binding.imageProductSpec)

        if (product!!.HasSponsor!!) {
            ImageLoader().get(requireContext(), product!!.SponsorImageKey!!, binding.imageProductSponsor)
        }

        specPrice = product!!.FullSalesPrice!!
        binding.edtxtProductQTY.setText(quantity.toString())
        binding.txtvProductTotalSalesPrice.text = Helper().priceFormatter(product!!.FullSalesPrice!!)

        if (product!!.DiscountedPrice!! > 0) {
            binding.txtvProductDiscountedPrice.visible()
            binding.imageProductPromo.visible()
            Helper().strikeTextView(binding.txtvProductDiscountedPrice, product!!.DiscountedPrice!!)
        } else {
            binding.txtvProductDiscountedPrice.gone()
            binding.imageProductPromo.gone()
        }

        binding.txtvProductDescription.text = product!!.SpecDescription
        binding.txtvProductUOM.text = product!!.OrderUnitType
        binding.txtvProductConfig.text = product!!.SpecConfiguration
        binding.txtvProductAdditionalInfo.text = product!!.AddedInformation

        if (product!!.AddedInformation?.count() ?: 0 > 0) {
            binding.cardAdditionalInfo.visible()
            if (binding.txtvProductAdditionalInfo.lineCount > 2) {
                binding.txtvProductAdditionalInfo.maxLines = 3
                binding.btnProductShowMoreHide.visible()
            } else {
                binding.btnProductShowMoreHide.gone()
            }
        }else{
            binding.cardAdditionalInfo.gone()
        }

        binding.txtvProductFullSalesPrice.text = Helper().priceFormatter(product!!.FullSalesPrice!!)

        if(product!!.IsLastOrdered != null) {
            if (product!!.IsLastOrdered!!) {
                binding.txtvProductLastOrderDate.visible()
                binding.txtvProductLastOrderDate.text = product!!.LastOrderDate
                binding.txtvProductLastOrderQuantity.visible()
                binding.txtvProductLastOrderQuantity.text = product!!.LastOrderQuantity.toString()
                if(quantity == 1) {
                    binding.edtxtProductPackSpec.setText(product!!.PackSpecification)
                    binding.edtxtProductCutSpec.setText(product!!.CutSpecification)
                    binding.edtxtProductQTY.setText(product!!.LastOrderQuantity.toString())
                }
            } else {
                binding.txtvProductLastOrderDate.gone()
                binding.txtvProductLastOrderQuantity.gone()
            }
        }else {
            binding.txtvProductLastOrderDate.gone()
            binding.txtvProductLastOrderQuantity.gone()
        }


        binding.imageProductSpec.layoutParams.height = specImage_height.toInt()
        binding.imageProductSpec.requestLayout()
        binding.txtvProductTotalSalesPrice.text = Helper().priceFormatter(quantity * product!!.FullSalesPrice!!)


        if (product!!.IsNew!!){
            binding.imageProductStar.visible()
            val id = requireContext().resources.getIdentifier("star_new", "drawable", requireContext().packageName)
            binding.imageProductStar.setImageResource(id)
        }else if (product!!.OrderFrequencyCount!! > 0){
            binding.imageProductStar.visible()
            val id = requireContext().resources.getIdentifier("star_freq", "drawable", requireContext().packageName)
            binding.imageProductStar.setImageResource(id)
        }else{
            binding.imageProductStar.gone()
        }
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order -> {
                val cartitems: MutableList<OrderItemModel> = PreferenceHelper().getOrderList()
                try {
                    if (!cartitems.isEmpty()) {
//                        if (binding.progProductSpecification.visibility == View.GONE) {
                            Navigation.findNavController(rootView).navigate(R.id.action_nav_product_spec_to_nav_cart)
//                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Please add an item to your order first", Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
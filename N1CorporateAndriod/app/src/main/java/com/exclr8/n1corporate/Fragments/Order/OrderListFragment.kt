package com.exclr8.n1corporate.Fragments.Order

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.exclr8.n1corporate.Adapters.CartItemAdapter
import com.exclr8.n1corporate.AsyncTasks.CartAsync
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentCartBinding

class OrderListFragment : Fragment(R.layout.fragment_cart) {
    lateinit var binding: FragmentCartBinding
    lateinit var defaultMenu: Menu
    lateinit var rootView: View
    lateinit var db: RoomDB
    var totalPrice: Double = 0.00
    var totalQTY : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater)
        rootView = binding.root
        setHasOptionsMenu(true)
        db = RoomDB.getInstance(requireContext())!!
        binding.btnPlaceOrder.isEnabled = false

        getData()

        binding.edtxtCustomerRef.setText(PreferenceHelper().getCustomerRef())

        binding.btnPlaceOrder.setOnClickListener { v ->
            if (PreferenceHelper().getOrderList() != null) {
                val bundle = Bundle()
                bundle.putDouble("Total", totalPrice)
                Navigation.findNavController(v).navigate(R.id.action_nav_cart_to_nav_delivery_type, bundle)
            } else {
                Toast.makeText(requireContext(), "Please add an item to your cart first. ", Toast.LENGTH_LONG).show()
            }
        }
        return rootView
    }

    fun getData(){
        CartAsync(requireContext()).get(){ Success, CartItems, CartItemSpecs, total, totalQTY ->
            requireActivity().runOnUiThread(){
                setData(CartItemSpecs, CartItems, totalQTY , total)
            }
        }
    }

    fun setData(products: MutableList<ProductSpecificationModel?>, cartitems: MutableList<OrderItemModel>?, totalQTY: Int, total: Double) {
        binding.progOrderList.gone()
        binding.btnPlaceOrder.isEnabled = true
        totalPrice = total
        this.totalQTY = totalQTY

        if (cartitems != null) {
            if (cartitems.count() > 0) {
                activity?.runOnUiThread {
                    binding.rcvCart.adapter = CartItemAdapter(products, cartitems, requireContext()) { PriceListItem, CartItem, adapterPosition ->
                        binding.progOrderList.visible()
                        if (PriceListItem != null && CartItem != null) {
                            val bundle = Bundle()
                            bundle.putInt("SalesItemRevisionId", CartItem.salesItemRevisionId)
                            Navigation.findNavController(rootView).navigate(R.id.action_nav_cart_to_nav_product_spec, bundle)
                        } else {
                            cartitems[adapterPosition!!]?.let { it1 ->
                                PreferenceHelper().removeCartItem(
                                    it1
                                )
                            }
                            getData()
//                            PreferenceHelper().addItem(CartItem!!)
                        }
                    }
                    binding.rcvCart.layoutManager = LinearLayoutManager(context)
                    binding.txtvDiscount.setText(Helper().priceFormatter(0.0))
                    binding.txtvExVat.setText(Helper().priceFormatter(total*0.85))
                    binding.txtvVat.setText(Helper().priceFormatter(total*0.15))
                    binding.txtvCartTotalPrice.setText(Helper().priceFormatter(total))
                    binding.txtvTotalTitle.text = "Total: $totalQTY item(s)"
                    (binding.rcvCart.adapter as CartItemAdapter).notifyDataSetChanged()
                }
            } else {
            Navigation.findNavController(rootView).popBackStack()
                Toast.makeText(activity, "Your cart is empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.cart_clear, menu)
        defaultMenu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_clear_cart -> {
                binding.progOrderList.visible()
                PreferenceHelper().clearCart()
                Navigation.findNavController(rootView).popBackStack()

            }
        }
        return super.onOptionsItemSelected(item)
    }

}
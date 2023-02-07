package com.exclr8.n1corporate.Fragments.Main.CreateOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.exclr8.n1corporate.Adapters.ProductsListAdapter
import com.exclr8.n1corporate.AsyncTasks.SponsorProductsAsync
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.AsyncTasks.ProductsAsync
import com.exclr8.n1corporate.AsyncTasks.SearchAsync
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.AsyncTasks.SpecialCategoryProductsAsync
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentProductListBinding
import com.exclr8.n1corporate.Fragments.CartNavFragment
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible

class ProductListFragment : CartNavFragment(R.layout.fragment_product_list) {
    lateinit var binding: FragmentProductListBinding
    lateinit var rootView: View
    val home = Helper()

    companion object{
        lateinit var oldTerm: String
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductListBinding.inflate(inflater)
        rootView = binding.root

        setHasOptionsMenu(true)
        binding. txtvProductListError.gone()



        if (requireArguments().getInt("Search") == 1) {
            binding.cardProductListSearch.visible()
            SearchAsync(requireContext(), requireArguments().getString("Term")!!).get { Success, Results ->
                activity?.runOnUiThread {
                    if(Success){
                        setData(Results!!)
                    }
                }
            }
            binding.edtxtProductListSearch.setText(requireArguments().getString("Term"))

            binding.edtxtProductListSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    oldTerm = binding.edtxtProductListSearch.text.toString()
                    requireArguments().putString("Term", oldTerm)
                    SearchAsync(requireContext(), oldTerm).get { Success, Results ->
                        activity?.runOnUiThread {
                            if (Success) {
                                setData(Results!!)
                            }
                        }
                    }

                    home.hideKeyboard(requireActivity())
                    return@OnEditorActionListener true
                }
                false
            })
        } else if (requireArguments().getString("SponsorKey") != null) {
            binding.cardProductListSearch.gone()
            SponsorProductsAsync(requireContext(), requireArguments().getString("SponsorKey")!!).get(){ Success, Data ->
                setData(Data)
            }
        }else if (requireArguments().getInt("SpecialProductCategoryID") != 0) {
            binding.cardProductListSearch.gone()
            SpecialCategoryProductsAsync().get(requireContext(), requireArguments().getInt("SpecialProductCategoryID")){ Data ->
                setData(Data)
            }
        }else{
            binding.cardProductListSearch.gone()
            ProductsAsync(requireContext(), requireArguments().getInt("ProductCategoryID")).get() { Data ->
                setData(Data)
            }
        }
        return rootView
    }

    fun setData(products: MutableList<ProductSpecificationModel?>) {
        //TABLET rendering support
        if (home.isTablet(requireContext())) {
            binding.rcvProductList.layoutManager = GridLayoutManager(context, home.calculateNoOfColumns(requireContext(), 180f), GridLayoutManager.VERTICAL, false)
        } else {
            binding.rcvProductList.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }

        //get data to display
        if (products.size > 0) {
            binding.rcvProductList.visible()
            binding.txtvProductListError.gone()
            binding.rcvProductList.adapter = ProductsListAdapter(products, false, requireContext())
            (binding.rcvProductList.adapter as ProductsListAdapter).notifyDataSetChanged()
        } else {
            binding.rcvProductList.gone()
            binding.txtvProductListError.visible()
        }

        binding.rcvProductList.scrollToPosition(Helper.productListScrollPosition)
        Helper.productListScrollPosition = 0
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order -> {
                val cartitems: MutableList<OrderItemModel> = PreferenceHelper().getOrderList()
                try {
                    if (!cartitems.isEmpty()) {
                        Navigation.findNavController(rootView!!).navigate(R.id.action_nav_productList_to_nav_cart)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "Please add an item to your order first", Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
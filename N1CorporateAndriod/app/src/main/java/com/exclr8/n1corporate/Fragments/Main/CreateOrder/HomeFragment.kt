package com.exclr8.n1corporate.Fragments.Main.CreateOrder

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exclr8.n1corporate.Adapters.CategoriesAdapter
import com.exclr8.n1corporate.Adapters.SearchAdapter
import com.exclr8.n1corporate.AsyncTasks.HomeScreenDataCallsAsync
import com.exclr8.n1corporate.AsyncTasks.SearchAsync
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductCategoryModel
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentHomeBinding
import com.exclr8.n1corporate.Fragments.CartNavFragment
import java.util.*
import androidx.recyclerview.widget.PagerSnapHelper

import androidx.core.content.ContextCompat
import com.exclr8.n1corporate.Activities.DashboardActivity
import com.exclr8.n1corporate.Adapters.SpecialCategoryAdapter
import com.exclr8.n1corporate.Adapters.SponsoredCategoryAdapter
import com.exclr8.n1corporate.AsyncTasks.ClearDatabaseAsync
import com.exclr8.n1corporate.AsyncTasks.SpecialCategoryModel
import com.exclr8.n1corporate.Helpers.*


class HomeFragment : CartNavFragment(R.layout.fragment_home){
    private lateinit var binding: FragmentHomeBinding
    lateinit var rootView: View
    lateinit var db: RoomDB

    val home = Helper()
    var searchTerm: String = ""



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        rootView = binding.root

        binding.btnCancelSearch.gone()
        binding.progLoadingHome.visible()

        HomeScreenDataCallsAsync(requireContext(), requireActivity()).getData { Success, CategoryList, SponsorList, SpecialCategoryList ->
            if(Success) {
                binding.progLoadingHome.gone()
                populateSponsoredCategories(SponsorList)
                populateSpecialCategories(SpecialCategoryList)
                populateCategories(CategoryList)

                binding.homeScrollView.post {
                    binding.homeScrollView.scrollY = Helper.homeFragmentScrollPosition
                    val radius = resources.getDimensionPixelSize(R.dimen.radius)
                    val dotsHeight = resources.getDimensionPixelSize(R.dimen.dots_height)
                    val color = ContextCompat.getColor(requireContext(), R.color.n1color)
                    binding.rcvSponsors.addItemDecoration(
                        DotsIndicatorDecoration(radius, radius * 3, dotsHeight, color, color)
                    )
                    PagerSnapHelper().attachToRecyclerView(binding.rcvSponsors)
                }
            }
        }

        binding.edtxtSearch.setOnTouchListener { v, event ->
            binding.txtvCategoriesTitle.gone()
            binding.rcvCategories.gone()
            binding.rcvSearch.visible()
            binding.btnCancelSearch.visible()
            false
        }

        binding.edtxtSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.edtxtSearch.text.toString() != "") {
                    home.hideKeyboard(this.requireActivity())
                    val bundle = Bundle()
                    bundle.putInt("Search", 1)
                    bundle.putString("Term", searchTerm)
                    clearSearch()
                    clearSearchText()
                    Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_productList, bundle)
                    return@OnEditorActionListener true
                } else {
                    return@OnEditorActionListener false
                }
            }
            false
        })
        binding.edtxtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (!binding.edtxtSearch.text.toString().isEmpty()) {
                    if (binding.edtxtSearch.text.toString() != "") {
                        showSearch()
                        searchTerm = binding.edtxtSearch.text.toString()
                        SearchAsync(requireContext(), searchTerm).get { Success, Results ->
                            if(Success){
                                activity?.runOnUiThread { populateSearchData(Results) }

                            }
                        }
                    } else {
                        clearSearch()
                    }
                } else {
                    clearSearch()
                }
            }
        })
        binding.btnCancelSearch.setOnClickListener {
            clearSearch()
            home.hideKeyboard(requireActivity())
            clearSearchText()
        }
        return rootView
    }




    fun clearSearchText() {
        binding.edtxtSearch.setText("")
        binding.edtxtSearch.clearFocus()
    }

    fun clearSearch() {
        binding.rcvSearch.gone()
        binding.btnCancelSearch.gone()
        binding.txtvCategoriesTitle.visible()
        binding.rcvCategories.visible()
        searchTerm = ""
    }

    fun showSearch() {
        binding.rcvSearch.visible()
        binding.btnCancelSearch.visible()
        binding.txtvCategoriesTitle.gone()
        binding.rcvCategories.gone()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().invalidateOptionsMenu()
        setHasOptionsMenu(true)


    }

    override fun onPause() {
        Helper.homeFragmentScrollPosition = binding.homeScrollView.scrollY
        super.onPause()
    }



    fun populateSearchData(searchList: List<ProductSpecificationModel?>?) {
            binding.rcvSearch.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rcvSearch.adapter = SearchAdapter(searchList!!) { priceListModel ->
                val bundle = Bundle()
                priceListModel.SalesItemRevisionId?.let { bundle.putInt("SalesItemRevisionId", it) }
                clearSearchText()
                Navigation.findNavController(rootView)
                    .navigate(R.id.action_nav_home_to_nav_product_spec, bundle)
            }
    }

    fun populateSponsoredCategories(sponsorsList: MutableList<String>?){
            binding.rcvSponsors.isNestedScrollingEnabled = false
            binding.rcvSponsors.layoutManager = GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
            binding.rcvSponsors.setHasFixedSize(true)
            binding.rcvSponsors.adapter = SponsoredCategoryAdapter(sponsorsList!!, requireContext()) { SponsorKey ->
                    val bundle = Bundle()
                    bundle.putString("SponsorKey", SponsorKey)
                    Navigation.findNavController(rootView).navigate(R.id.action_nav_home_to_nav_productList, bundle)
                }

    }

    fun populateSpecialCategories(specialCategoryList: List<SpecialCategoryModel?>?) {
        if (specialCategoryList != null) {
            if (specialCategoryList.count() > 0){
                binding.rcvSpecialCategories.visibility =
                View.VISIBLE
            binding.spacerSpecialCategories.visible()
            binding.rcvSpecialCategories.adapter = SpecialCategoryAdapter(
                specialCategoryList,
                requireContext()
            ) { SpecialProductCategoryID ->
                ProductListFragment().arguments?.remove("SponsorKey")
                ProductListFragment().arguments?.remove("ProductCategoryID")
                val bundle = Bundle()
                bundle.putInt("SpecialProductCategoryID", SpecialProductCategoryID)
                Navigation.findNavController(rootView)
                    .navigate(R.id.action_nav_home_to_nav_productList, bundle)
            }
            binding.rcvSpecialCategories.layoutManager = GridLayoutManager(
                requireContext(),
                home.calculateNoOfColumns(requireContext(), 350f)
            )
        } else {
            binding.rcvSpecialCategories.gone()
            binding.spacerSpecialCategories.gone()
        }
    }
    }

    fun populateCategories(productCategoryModelList: List<ProductCategoryModel?>?) {
            binding.rcvCategories.adapter = CategoriesAdapter(productCategoryModelList!!, requireContext()) { ProductCategoryId ->
                ProductListFragment().arguments?.remove("SponsorKey")
                ProductListFragment().arguments?.remove("SpecialProductCategoryID")
                val bundle = Bundle()
                bundle.putInt("ProductCategoryID", ProductCategoryId)
                Navigation.findNavController(rootView).navigate(R.id.action_nav_home_to_nav_productList, bundle)
            }
            binding.rcvCategories.layoutManager = GridLayoutManager(requireContext(), home.calculateNoOfColumns(requireContext(), 350f))
            binding.txtvCategoriesTitle.visible()
    }


    //Override android system back button in DashboardActivity.kt
    fun onBackPressed(context: Context, callback: (Boolean) -> Unit){
        DashboardActivity().showAlertDailog(context){ returnValue ->
            if (returnValue) {
                Helper.homeFragmentScrollPosition = 0
                PreferenceHelper().clearCart()
                ClearDatabaseAsync(context).clearProducts()
                callback(true)
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order -> {
                val cartitems: MutableList<OrderItemModel> = PreferenceHelper().getOrderList()
                try {
                    if (!cartitems.isEmpty()) {
                        Navigation.findNavController(rootView).navigate(R.id.action_nav_home_to_nav_cart)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Please add an item to your order first", Toast.LENGTH_LONG).show()
                }
            }

            //Override navigation bar back button
            android.R.id.home -> {
                DashboardActivity().showAlertDailog(requireContext()){ returnValue ->
                    if (returnValue) {
                        Helper.homeFragmentScrollPosition = 0
                        PreferenceHelper().clearCart()
                        ClearDatabaseAsync(requireContext()).clearProducts()
                        Navigation.findNavController(rootView).navigateUp()
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
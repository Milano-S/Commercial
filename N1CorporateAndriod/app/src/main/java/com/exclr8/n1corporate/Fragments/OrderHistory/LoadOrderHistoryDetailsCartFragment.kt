package com.exclr8.n1corporate.Fragments.OrderHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.exclr8.n1corporate.APIHelpers.Login.ProductCategoriesAPI
import com.exclr8.n1corporate.APIHelpers.Login.ProductSpecificationsAPI
import com.exclr8.n1corporate.Activities.LoadingActivity
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.State
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentLoadOrderHistoryDetailsCartBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LoadOrderHistoryDetailsCartFragment: Fragment(R.layout.activity_loading_screen) {
    lateinit var binding: FragmentLoadOrderHistoryDetailsCartBinding
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoadOrderHistoryDetailsCartBinding.inflate(inflater)
        rootView = binding.root

        Helper().animateImageLoading(binding.imageLoadOrderHistory, requireContext())

        GlobalScope.async {
            ProductCategoriesAPI(requireContext()).get { Success, Offline, Exception ->
                if(Success){
                    if(!Offline){
                        ProductSpecificationsAPI(requireContext()).get { Success, Offline, Exception ->
                            if(Success){
                                if(!Offline){
                                    requireActivity().runOnUiThread {
                                        Navigation.findNavController(rootView).navigate(R.id.action_nav_load_order_history_cart_to_nav_home)
                                    }
                                }else{
                                    State().showOffline(requireContext(), requireActivity())
                                }
                            }
                        }
                    }else{
                        State().showOffline(requireContext(), requireActivity())
                    }
                }
            }
        }

        return rootView
    }
}
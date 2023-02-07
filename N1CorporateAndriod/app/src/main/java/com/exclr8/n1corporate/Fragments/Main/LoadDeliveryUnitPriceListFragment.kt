package com.exclr8.n1corporate.Fragments.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.exclr8.n1corporate.APIHelpers.Login.ImageKeyLoaderAPI
import com.exclr8.n1corporate.APIHelpers.Login.ProductCategoriesAPI
import com.exclr8.n1corporate.APIHelpers.Login.ProductSpecificationsAPI
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.Helpers.State
import com.exclr8.n1corporate.databinding.FragmentLoadDeliveryUnitPriceListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LoadDeliveryUnitPriceListFragment : Fragment(R.layout.fragment_load_delivery_unit_price_list) {
    lateinit var binding: FragmentLoadDeliveryUnitPriceListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoadDeliveryUnitPriceListBinding.inflate(inflater)


        Helper().animateImageLoading(binding.imageLoadDeliveryUnitTruck, requireContext())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        PreferenceHelper().clearCart()

        GlobalScope.async {
            ProductCategoriesAPI(requireContext()).get { Success, Offline, Exception ->
                if(Success){
                    if(!Offline){
                        ProductSpecificationsAPI(requireContext()).get { Success, Offline, Exception ->
                            if(Success){
                                if(!Offline){
                                    requireActivity().runOnUiThread {
                                        Navigation.findNavController(view).navigate(R.id.action_nav_loadPriceList_to_nav_home)
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

    }

}
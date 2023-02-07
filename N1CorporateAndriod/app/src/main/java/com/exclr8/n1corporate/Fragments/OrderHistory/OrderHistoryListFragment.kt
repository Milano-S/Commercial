package com.exclr8.n1corporate.Fragments.OrderHistory

import android.os.Bundle
import android.provider.DocumentsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.exclr8.n1corporate.APIHelpers.OrderHistory.OrderHistoryAPI
import com.exclr8.n1corporate.Adapters.OrderHistoryAdapter
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.OrderHistoryModel
import com.exclr8.n1corporate.DataModels.ProductModels.OrderProductSpecificationModel
import com.exclr8.n1corporate.Helpers.State
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentOrderHistoryBinding

class OrderHistoryListFragment : Fragment(R.layout.fragment_order_history) {
    lateinit var binding: FragmentOrderHistoryBinding
    lateinit var rootView: View
    lateinit var db: RoomDB


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderHistoryBinding.inflate(inflater)
        rootView = binding.root

        binding.txtvNoHistory.gone()
        db = RoomDB.getInstance(requireContext())!!
        binding.progOrderHistoryList.visible()

        OrderHistoryAPI().get { Success, Offline, Exception, OrderList ->
            activity?.runOnUiThread {
                if (Success) {
                    if (!Offline) {
                        populateList(OrderList!!)
                    } else {
                        State().showOffline(requireContext(), requireActivity())
                    }
                } else {
                    State().showCustomErrorToastOnUIThread(requireContext(), requireActivity(), Exception)
                }
            }
        }
        return rootView
    }


    fun populateList(ordersList: List<OrderHistoryModel?>) {
        binding.progOrderHistoryList.gone()
        if (ordersList.isEmpty()) {
            binding.txtvNoHistory.visible()
        } else {
            binding.rcvOrderHistoryList.layoutManager = LinearLayoutManager(requireContext())
            binding.rcvOrderHistoryList.adapter = OrderHistoryAdapter(ordersList){OrderHistoryModel ->
                val bundle = Bundle()
                bundle.putParcelable("OrderProductSpecificationModel", OrderHistoryModel)
                Navigation.findNavController(rootView).navigate(R.id.action_nav_history_to_nav_history_details, bundle)
            }
        }
    }
}
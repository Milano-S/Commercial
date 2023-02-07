package com.exclr8.n1corporate.Fragments.Main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.exclr8.n1corporate.Adapters.SelectDeliveryUnitAdapter
import com.exclr8.n1corporate.AsyncTasks.DeliveryUnitsAsync
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentSelectDeliveryUnitBinding

class SelectDeliveryUnitFragment: Fragment(R.layout.fragment_select_delivery_unit){
    lateinit var binding: FragmentSelectDeliveryUnitBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectDeliveryUnitBinding.bind(view)

        DeliveryUnitsAsync().get(requireContext()){deliveryUnits ->
            requireActivity().runOnUiThread{
                initializeRCVSelectDeliveryUnit(deliveryUnits)
            }
        }

        binding.edtxtSelectDeliveryUnitSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                DeliveryUnitsAsync().search("%" + p0 + "%", requireContext()){deliveryUnits ->
                    requireActivity().runOnUiThread{
                        initializeRCVSelectDeliveryUnit(deliveryUnits)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    fun initializeRCVSelectDeliveryUnit(deliveryUnits: MutableList<DeliveryUnitModel>){
        binding.rcvSelectDeliveryUnit.layoutManager = LinearLayoutManager(context)
        binding.rcvSelectDeliveryUnit.adapter = SelectDeliveryUnitAdapter(deliveryUnits, requireContext(), false){ it ->
            Navigation.findNavController(binding.root).navigate(R.id.action_nav_selectDeliveryUnit_to_nav_loadPriceList)
        }
        (binding.rcvSelectDeliveryUnit.adapter as SelectDeliveryUnitAdapter).notifyDataSetChanged()
    }
}
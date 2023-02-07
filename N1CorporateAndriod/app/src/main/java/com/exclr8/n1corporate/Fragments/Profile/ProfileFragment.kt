package com.exclr8.n1corporate.Fragments.Profile

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.withTransaction
import com.exclr8.n1corporate.Adapters.SelectDeliveryUnitAdapter
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.exclr8.n1corporate.DataModels.GenericModels.UserModel
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentProfileDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class ProfileFragment : Fragment(R.layout.fragment_profile_details) {
    lateinit var binding: FragmentProfileDetailsBinding
    lateinit var rootView: View
    lateinit var db: RoomDB


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileDetailsBinding.inflate(inflater)
        rootView = binding.root


        populateData()
        return rootView
    }

    override fun onResume(){
        super.onResume()
        populateData()
    }

    fun populateData(){
        binding.progProfileDetails.visible()

        GlobalScope.launch {
            db = RoomDB.getInstance(requireContext())!!
            val userData = db.userDao().getUserModel()
            val deliveryUnitsData = db.deliveryUnitsDao().getDeliveryUnits()
            requireActivity().runOnUiThread {

                if(userData.FirstName != null){
                    binding.progProfileDetails.gone()
                    binding.txtvFirstName.text = userData.FirstName
                    binding.txtvLastName.text = userData.LastName
                    binding.txtvPhone.text = PhoneNumberUtils.formatNumber("+" + userData.PhoneNumber, Locale.getDefault().country)
                    binding.txtvEmail.text = userData.EmailAddress
                    binding.rcvDeliveryUnits.layoutManager = LinearLayoutManager(context)
                    binding.rcvDeliveryUnits.adapter = SelectDeliveryUnitAdapter(deliveryUnitsData, requireContext(), true) {}
                    (binding.rcvDeliveryUnits.adapter as SelectDeliveryUnitAdapter).notifyDataSetChanged()
                }else{
                    populateData()
            }
        }

        }
    }

}
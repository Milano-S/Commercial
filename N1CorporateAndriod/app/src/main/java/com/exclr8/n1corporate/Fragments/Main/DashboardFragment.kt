package com.exclr8.n1corporate.Fragments.Main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.Activities.LoadingActivity
import com.exclr8.n1corporate.Activities.LoginActivity
import com.exclr8.n1corporate.Adapters.DashboardAdapter
import com.exclr8.n1corporate.AsyncTasks.ClearDatabaseAsync
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.GenericModels.MenuOptionsModel
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.ActivityDashboardBinding
import com.exclr8.n1corporate.databinding.FragmentDashboardBinding

class DashboardFragment: Fragment() {
    lateinit var rootView: View
    lateinit var binding: FragmentDashboardBinding
    lateinit var defaultMenu: Menu

    lateinit var menuOptions: MutableList<MenuOptionsModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(inflater)
        rootView = binding.root
        setHasOptionsMenu(true)

        menuOptions = mutableListOf()
        menuOptions.add(MenuOptionsModel(1,requireContext().getResources().getIdentifier("create", "drawable", requireContext().getPackageName()), "Create New Order"))
        menuOptions.add(MenuOptionsModel(2,requireContext().getResources().getIdentifier("history", "drawable", requireContext().getPackageName()), "View Order History"))
        menuOptions.add(MenuOptionsModel(3,requireContext().getResources().getIdentifier("profile", "drawable", requireContext().getPackageName()), "My Profile"))
        menuOptions.add(MenuOptionsModel(4,requireContext().getResources().getIdentifier("contact", "drawable", requireContext().getPackageName()), "Contact Us"))
        menuOptions.add(MenuOptionsModel(5,requireContext().getResources().getIdentifier("about", "drawable", requireContext().getPackageName()), "About Us"))

        initializeRCVSelectDeliveryUnit()
        return rootView
    }



    fun initializeRCVSelectDeliveryUnit(){
        binding.rcvDashboard.layoutManager = GridLayoutManager(activity,3, )

        binding.rcvDashboard.adapter = DashboardAdapter(menuOptions, requireContext()){ it ->
            when (it){
                1 -> Navigation.findNavController(rootView).navigate(R.id.action_nav_dashboard_to_nav_selectDeliveryUnit)

                2 -> Navigation.findNavController(rootView).navigate(R.id.action_nav_dashboard_to_nav_history)

                3-> Navigation.findNavController(rootView).navigate(R.id.action_nav_dashboard_to_nav_profile)

                4 -> Navigation.findNavController(rootView).navigate(R.id.action_nav_dashboard_to_nav_contact)

                5 -> Navigation.findNavController(rootView).navigate(R.id.action_nav_dashboard_to_nav_about)

            }
        }
        (binding.rcvDashboard.adapter as DashboardAdapter).notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard_menu, menu)
        defaultMenu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                ClearDatabaseAsync(requireContext()).clear()
                PreferenceHelper().clearPreferences()
                val loading = Intent(requireContext(), LoadingActivity::class.java)
                loading.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                requireActivity().finish()
                this.startActivity(loading)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
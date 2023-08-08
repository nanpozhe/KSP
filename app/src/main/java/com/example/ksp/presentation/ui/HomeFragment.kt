package com.example.ksp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentHomeBinding
import com.example.ksp.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    private lateinit var homeBinding: FragmentHomeBinding

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // data bidding
        homeBinding.homeFragment = this

        homeBinding.userFullName.text = homeViewModel.username
    }

/*    override fun onResume() {
        super.onResume()
        parentFragmentManager.popBackStack(R.id.action_loginFragment_to_homeFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
*/
    fun toSettingsPage(){
        findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
    }

    fun toCouncilPage(){
        findNavController().navigate(R.id.action_homeFragment_to_councilFragment)
    }

    fun toTopUpPage(){
        findNavController().navigate(R.id.action_homeFragment_to_topUpFragment)
    }
}
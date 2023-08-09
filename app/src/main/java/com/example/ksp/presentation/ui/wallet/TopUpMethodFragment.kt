package com.example.ksp.presentation.ui.wallet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.data.util.Utils.validateTopUpRequest
import com.example.ksp.databinding.FragmentTopUpBinding
import com.example.ksp.databinding.FragmentTopUpMethodBinding
import com.example.ksp.presentation.viewmodel.TopUpViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopUpMethodFragment : Fragment() {

    val sharedViewModel: TopUpViewModel by activityViewModels()
    private var topUpMethodBinding: FragmentTopUpMethodBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentTopUpMethodBinding.inflate(inflater, container, false)
        topUpMethodBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topUpMethodBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            topUpMethodFragment = this@TopUpMethodFragment
        }

        sharedViewModel.amount.observe(viewLifecycleOwner){ amount ->
            if(amount == 0 || amount == null){
                Snackbar.make(view, "Amount null, damn", Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_topUpMethodFragment_to_topUpFragment)
            } else {
                topUpMethodBinding?.amountConfirmText?.setText("Top-up Amount : RM$amount")
            }
        }

        sharedViewModel.successful.observe(viewLifecycleOwner){ successful ->
            if(successful== true){
                topUpMethodBinding?.loginProgress?.loadingProgress?.visibility = View.INVISIBLE
                topUpMethodBinding?.chooseMethodSection?.isEnabled = true
                topUpMethodBinding?.topUpNowButton?.isEnabled = true
                findNavController().navigate(R.id.action_topUpMethodFragment_to_homeFragment)
            } else if(successful == false) {
                topUpMethodBinding?.loginProgress?.loadingProgress?.visibility = View.INVISIBLE
                topUpMethodBinding?.chooseMethodSection?.isEnabled = true
                topUpMethodBinding?.topUpNowButton?.isEnabled = true
                Snackbar.make(view, "${sharedViewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                sharedViewModel.navigateToPage()
            }
        }
    }

    fun topUp(){
        val validateResult = validateTopUpRequest(
            amount = sharedViewModel.amount.value!!,
            method = sharedViewModel.method.value!!)

        if(validateResult.successful){
            topUpMethodBinding?.loginProgress?.loadingProgress?.visibility = View.VISIBLE
            topUpMethodBinding?.chooseMethodSection?.isEnabled = false
            topUpMethodBinding?.topUpNowButton?.isEnabled = false
        } else {
            Snackbar.make(requireView(), "${validateResult.error}", Snackbar.LENGTH_LONG).show()
        }
        sharedViewModel.topUp()
    }
}
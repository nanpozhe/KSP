package com.example.ksp.presentation.ui.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentTopUpBinding
import com.example.ksp.presentation.viewmodel.TopUpViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopUpFragment : Fragment() {

    companion object {
        fun newInstance() = TopUpFragment()
    }

    val viewModel: TopUpViewModel by activityViewModels()
    private var topUpBinding: FragmentTopUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentTopUpBinding.inflate(inflater, container, false)
        topUpBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topUpBinding?.topUpFragment = this

        topUpBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            topUpFragment = this@TopUpFragment
        }

        viewModel.getAccountId()
        viewModel.successful.observe(viewLifecycleOwner){ successful ->
            if(successful == false){
                Snackbar.make(view, "Failed to retrieve wallet id... Please contact admin", Snackbar.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_topUpFragment_to_homeFragment)
            }
            viewModel.navigateToPage()
        }
    }

    fun amountButtonAction(amount: Int){
        viewModel.setAmount(amount)
        /** after set the amount in vm, redirect to method page
         *  remove the method section -> a new page with amount displayed above the top up button
         *  exactly like setel app one
         **/
        findNavController().navigate(R.id.action_topUpFragment_to_topUpMethodFragment)
    }

    fun otherAmountButtonAction(){
        val bottomSheetFragment = ModalBottomSheetFragment()
        bottomSheetFragment.show(requireFragmentManager(), "Top-Up Amount")
    }

    private fun disableButtonsInLayout(layout: ViewGroup){
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is Button) {
                child.isEnabled = false
            } else if (child is ViewGroup) {
                disableButtonsInLayout(child) // Recursively disable buttons in child layouts
            }
        }
    }

}
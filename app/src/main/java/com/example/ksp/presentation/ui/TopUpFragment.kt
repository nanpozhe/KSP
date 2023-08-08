package com.example.ksp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
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

    val viewModel: TopUpViewModel by viewModels()
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

        if(!viewModel.getAccountId()){          // if fail
            Snackbar.make(view, "Failed to retrieve wallet id... Please contact admin", Snackbar.LENGTH_LONG).show()
            val rootLayout = topUpBinding?.root?.findViewById<ViewGroup>(R.id.topUpFragment)
            disableButtonsInLayout(rootLayout!!)
        }
    }

    fun topUpNowButton(){
        viewModel.topUp()
    }

    fun amountButtonAction(amount: Int){
        viewModel.setAmount(amount)
        /** after set the amount in vm, redirect to method page
         *  remove the method section -> a new page with amount displayed above the top up button
         *  exactly like setel app one
         **/
        //findNavController().navigate()
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
package com.example.ksp.presentation.ui.wallet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentTopUpMethodBinding
import com.example.ksp.databinding.TopUpModalBottomSheetBinding
import com.example.ksp.presentation.viewmodel.TopUpViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetFragment: BottomSheetDialogFragment() {

    val sharedViewModel: TopUpViewModel by activityViewModels()
    private var fragmentModalBottomSheetBinding: FragmentTopUpMethodBinding? = null
    private lateinit var modalBottomSheetBinding: TopUpModalBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_up_modal_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modalBottomSheetBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }

        modalBottomSheetBinding.confirmTopUpButton.setOnClickListener {
            hideSoftKeyboard()

            val amount = modalBottomSheetBinding.otherAmountEditText.text.toString().toInt()
            sharedViewModel.setAmount(amount)
            findNavController().navigate(R.id.action_topUpFragment_to_topUpMethodFragment)
        }
    }

    private fun hideSoftKeyboard(){
        // Get the input method manager in fragments of onViewCreated need requireActivity()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
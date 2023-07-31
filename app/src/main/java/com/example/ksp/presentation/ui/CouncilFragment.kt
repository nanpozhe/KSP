package com.example.ksp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentCouncilBinding
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CouncilFragment : Fragment() {

    companion object {
        fun newInstance() = CouncilFragment()
    }

    val viewModel: CouncilViewModel by viewModels()
    private lateinit var councilBinding: FragmentCouncilBinding
    val councils = mutableMapOf<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val radioGroupPenang: RadioGroup = councilBinding.penangState               // Get the RadioGroup by its ID
        val radioGroupPerak: RadioGroup = councilBinding.perakState
        val radioGroupSelangor: RadioGroup = councilBinding.selangorState
        val radioGroups = arrayOf(radioGroupPenang, radioGroupPerak, radioGroupSelangor)        // set all states' radio group into a single array

        val radioButtonIds = ArrayList<Int>()                                       // Create an array to store radio button IDs, to be reused
        for (e in 0 until (radioGroups.size-1)){
            for(i in 0 until radioGroups[e].childCount){                        // Loop through the child views of the RadioGroup
                val view = radioGroups[e].getChildAt(i)
                if(view is RadioButton){
                    radioButtonIds.add(view.id)                                        // Add the radio button ID to the array
                }
                Log.i("MainActivity", "${radioButtonIds[i]}")
            }
        }


        val penangCouncilNameArr = resources.getStringArray(R.array.penang_council_array)
        val perakCouncilNameArr = resources.getStringArray(R.array.perak_council_array)
        val selangorCouncilNameArr = resources.getStringArray(R.array.selangor_council_array)
        Log.i("MainActivity", "Council Fragment -> \b ${penangCouncilNameArr} \b ${perakCouncilNameArr} \b ${selangorCouncilNameArr}")
        val allCouncilNames = arrayOf<String>()
        for (i in 0 until penangCouncilNameArr.lastIndex)
            allCouncilNames.set(i, penangCouncilNameArr[i])
        for (i in 0 until perakCouncilNameArr.lastIndex){
            var e = allCouncilNames.size
            Log.i("MainActivity", "value of e for 2nd loop = ${e}")
            allCouncilNames.set(e, perakCouncilNameArr[i])
            e++
        }
        for (i in 0 until selangorCouncilNameArr.lastIndex){
            var e = allCouncilNames.size
            Log.i("MainActivity", "value of e for 3rd loop = ${e}")
            allCouncilNames.set(e, selangorCouncilNameArr[i])
            e++
        }

        for (i in 0 until radioButtonIds.lastIndex){
            councils.put(radioButtonIds[i], allCouncilNames[i])
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCouncilBinding.inflate(inflater, container, false)
        councilBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun onRadioButtonClicked(view: View){
        if(view is RadioButton){
            // Is the button now checked?
            val checked = view.isChecked
            val found = false
            val i = 0
            // Check which button is checked and retrieve the value from the matched key
            var result = councils.filterKeys { it == view.id }
            Log.i("MainActivity", "CouncilFragment -> view:${view.text}&${view.id}  ||  key:${councils}")
            Snackbar.make(view, "${view.text} is selected", Snackbar.LENGTH_LONG).show()
        }
    }
}
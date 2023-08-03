package com.example.ksp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentCouncilBinding
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CouncilFragment : Fragment() {

    companion object {
        fun newInstance() = CouncilFragment()
    }

    val viewModel: CouncilViewModel by viewModels()
    private lateinit var councilBinding: FragmentCouncilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCouncilBinding.inflate(inflater, container, false)
        councilBinding = fragmentBinding
        return fragmentBinding.root
    }

    fun toPenangCouncilPage(){
        findNavController().navigate(R.id.action_councilFragment_to_penangCouncilFragment)
    }

    fun toPerakCouncilPage(){
        findNavController().navigate(R.id.action_councilFragment_to_perakCouncilFragment)
    }

    fun toSelangorCouncilPage(){
        findNavController().navigate(R.id.action_councilFragment_to_selangorCouncilFragment)
    }
}


/** TO BE RESEARCHED LATER **/
/*  val councils = mutableMapOf<Int, String>()
    fun storeAllCouncils() {
        val radioGroupPenang: RadioGroup = councilBinding.penangState               // Get the RadioGroup by its ID
        val radioGroupPerak: RadioGroup = councilBinding.perakState
        val radioGroupSelangor: RadioGroup = councilBinding.selangorState
        val radioGroups = arrayOf(
            radioGroupPenang,
            radioGroupPerak,
            radioGroupSelangor
        )        // set all states' radio group into a single array

        val radioButtonIds = ArrayList<Int>()                                       // Create an array to store radio button IDs, to be reused
        for (e in 0 until (radioGroups.size - 1)) {
            if(e==0){                                                                       // 1st loop for penang state
                for (i in 0 until radioGroups[e].childCount) {                        // Loop through the child views of the RadioGroup
                    val view = radioGroups[e][i]
                    if (view is RadioButton) {
                        radioButtonIds.add(view.id)                                        // Add the radio button ID to the array
                        Log.i("MainActivity", "${radioButtonIds[i]}")
                    }
                }
            } else if(e==1){
                val p = radioGroupPenang.size
                var q = 0
                for (i in p until radioGroups[e].childCount) {                        // Loop through the child views of the RadioGroup
                    val view = radioGroups[e][q]
                    if (view is RadioButton) {
                        radioButtonIds.add(view.id)                                        // Add the radio button ID to the array
                        Log.i("MainActivity", "${radioButtonIds[i]}")
                    }
                    q++
                }
            } else if(e==2){
                val p = radioGroupPenang.size + radioGroupPerak.size
                var t = 0
                for (i in p until radioGroups[e].childCount) {                        // Loop through the child views of the RadioGroup
                    val view = radioGroups[e][t]
                    if (view is RadioButton) {
                        radioButtonIds.add(view.id)                                        // Add the radio button ID to the array
                        Log.i("MainActivity", "${radioButtonIds[i]}")
                    }
                    t++
                }
            }
        }


        val penangCouncilNameArr = councilBinding.root.resources.getStringArray(R.array.penang_council_array)
        val perakCouncilNameArr = councilBinding.root.resources.getStringArray(R.array.perak_council_array)
        val selangorCouncilNameArr = councilBinding.root.resources.getStringArray(R.array.selangor_council_array)
        Log.i("MainActivity", "Council Fragment -> ${penangCouncilNameArr} ${perakCouncilNameArr} ${selangorCouncilNameArr}")
        val allCouncilNames = arrayOf<String>()
        for (i in 0 until penangCouncilNameArr.lastIndex) {
            Log.d("MainActivity", "Council Fragment -> $i")
            allCouncilNames[i] = penangCouncilNameArr[i]
        }
        for (i in 0 until perakCouncilNameArr.lastIndex) {
            var e = allCouncilNames.size
            Log.i("MainActivity", "value of e for 2nd loop = ${e}")
            allCouncilNames[e] = perakCouncilNameArr[i]
            e++
        }
        for (i in 0 until selangorCouncilNameArr.lastIndex) {
            var e = allCouncilNames.size
            Log.i("MainActivity", "value of e for 3rd loop = ${e}")
            allCouncilNames[e] = selangorCouncilNameArr[i]
            e++
        }

        for (i in 0 until radioButtonIds.lastIndex) {
            councils.put(radioButtonIds[i], allCouncilNames[i])
        }
    }

    // TO BE CALLED ON LAYOUT XML
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
   */
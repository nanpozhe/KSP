package com.example.ksp.presentation.viewmodel

import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ksp.R
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.databinding.FragmentCouncilBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CouncilViewModel @Inject constructor(
    private val sharedPreference: SharedPreference
): ViewModel() {

    private val _councilName = MutableLiveData<String>()
    val councilName: LiveData<String> = _councilName

    /** setter functions **/
    fun setCouncilName(cn: String){
        _councilName.value = cn
    }

    fun saveCouncil(id: Int, name: String) {
        sharedPreference.saveCouncilSelectedId(id)
        sharedPreference.saveCouncilName(councilName.value.orEmpty())
    }

    fun getCouncilName(): String{
        return sharedPreference.getCouncilName()
    }

    fun getCouncilId(): Int{
        return sharedPreference.getCouncilSelectedId()
    }
}

/*    val councils = mutableMapOf<Int, String>()

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
            for (i in 0 until radioGroups[e].childCount) {                        // Loop through the child views of the RadioGroup
                val view = radioGroups[e].getChildAt(i)
                if (view is RadioButton) {
                    radioButtonIds.add(view.id)                                        // Add the radio button ID to the array
                    Log.i("MainActivity", "${radioButtonIds[i]}")
                }
            }
        }


        val penangCouncilNameArr = councilBinding.root.resources.getStringArray(R.array.penang_council_array)
        val perakCouncilNameArr = councilBinding.root.resources.getStringArray(R.array.perak_council_array)
        val selangorCouncilNameArr = councilBinding.root.resources.getStringArray(R.array.selangor_council_array)
        Log.i("MainActivity", "Council Fragment -> \b ${penangCouncilNameArr} \b ${perakCouncilNameArr} \b ${selangorCouncilNameArr}")
        val allCouncilNames = arrayOf<String>()
        for (i in 0 until penangCouncilNameArr.lastIndex)
            allCouncilNames.set(i, penangCouncilNameArr[i])
        for (i in 0 until perakCouncilNameArr.lastIndex) {
            var e = allCouncilNames.size
            Log.i("MainActivity", "value of e for 2nd loop = ${e}")
            allCouncilNames.set(e, perakCouncilNameArr[i])
            e++
        }
        for (i in 0 until selangorCouncilNameArr.lastIndex) {
            var e = allCouncilNames.size
            Log.i("MainActivity", "value of e for 3rd loop = ${e}")
            allCouncilNames.set(e, selangorCouncilNameArr[i])
            e++
        }

        for (i in 0 until radioButtonIds.lastIndex) {
            councils.put(radioButtonIds[i], allCouncilNames[i])
        }
    }  */
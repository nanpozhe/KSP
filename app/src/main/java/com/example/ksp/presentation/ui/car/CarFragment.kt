package com.example.ksp.presentation.ui.car

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment.STYLE_NO_FRAME
import androidx.fragment.app.activityViewModels
import com.example.ksp.R
import com.example.ksp.databinding.FragmentCarBinding
import com.example.ksp.presentation.viewmodel.CarViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarFragment : Fragment() {

    companion object {
        fun newInstance() = CarFragment()
    }

    val viewModel: CarViewModel by activityViewModels()
    private var carBinding: FragmentCarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCarBinding.inflate(inflater, container, false)
        carBinding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carBinding?.carFragment  =this

        carBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            carFragment = this@CarFragment
        }

        // get & display the selected car on top of fragment
        carBinding?.carSelectedValue?.text = viewModel.getCarPlate()

        // get car info and display it out
        viewModel.getCarInfo()
        disablePage()
        viewModel.successfulCarInfo.observe(viewLifecycleOwner){ successful ->
            if(successful == true){
                enablePage()
                for(i in 0..4){
                    Log.d("MainActivity", "CarFragment -> getting button $i")
                    if(viewModel.keys[i]?.contentEquals("CREATE") == false){
                        when(i) {
                            0 -> carBinding?.carButton1?.text = viewModel.keys[i]
                            1 -> carBinding?.carButton2?.text = viewModel.keys[i]
                            2 -> carBinding?.carButton3?.text = viewModel.keys[i]
                            3 -> carBinding?.carButton4?.text = viewModel.keys[i]
                            4 -> carBinding?.carButton5?.text = viewModel.keys[i]
                        }
                    } else if(viewModel.keys[i]?.contentEquals("CREATE") == true){
                        when(i) {
                            0 -> carBinding?.carButton1?.text = resources.getString(R.string.create_car)
                            1 -> carBinding?.carButton2?.text = resources.getString(R.string.create_car)
                            2 -> carBinding?.carButton3?.text = resources.getString(R.string.create_car)
                            3 -> carBinding?.carButton4?.text = resources.getString(R.string.create_car)
                            4 -> carBinding?.carButton5?.text = resources.getString(R.string.create_car)
                        }
                    }
                }
            } else if(successful == false){
                enablePage()
                Snackbar.make(view, "${viewModel.error.value}", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun deleteCar(btn_id: View){
        when(btn_id.id){                   // check which button is clicked
            R.id.delete_car_1 -> callDeleteCar(carBinding?.carButton1?.text.toString())
            R.id.delete_car_2 -> callDeleteCar(carBinding?.carButton2?.text.toString())
            R.id.delete_car_3 -> callDeleteCar(carBinding?.carButton3?.text.toString())
            R.id.delete_car_4 -> callDeleteCar(carBinding?.carButton4?.text.toString())
            R.id.delete_car_5 -> callDeleteCar(carBinding?.carButton5?.text.toString())
        }
    }

    // select and save the car plate into app pref
    fun carButtonAction(view: View){
        Log.d("MainActivity", "CarFragment -> $view |~~~| ${view.id}")
        when(view.id){
            R.id.car_button_1 ->
                if ((view as Button).text.toString() == "Create" || (view as Button).text.toString() == "create" || (view as Button).text.toString() == "CREATE"){
                    Log.d("MainActivity", "CarFragment -> Showing dialog // ${(view as Button).text.toString()}")
                    showDialog()
                } else {
                    Log.d("MainActivity", "CarFragment -> saving car plate // ${(view as Button).text.toString()}")
                    viewModel.saveCarPlate((view as Button).text.toString())
                    carBinding?.carSelectedValue?.text = view.text.toString()
                }
            R.id.car_button_2 ->
                if ((view as Button).text.toString() == "Create" || (view as Button).text.toString() == "create" || (view as Button).text.toString() == "CREATE"){
                    Log.d("MainActivity", "CarFragment -> Showing dialog // ${(view as Button).text.toString()}")
                    showDialog()
                } else {
                    Log.d("MainActivity", "CarFragment -> saving car plate // ${(view as Button).text.toString()}")
                    viewModel.saveCarPlate((view as Button).text.toString())
                    carBinding?.carSelectedValue?.text = view.text.toString()
                }
            R.id.car_button_3 ->
                if ((view as Button).text.toString() == "Create" || (view as Button).text.toString() == "create" || (view as Button).text.toString() == "CREATE"){
                    Log.d("MainActivity", "CarFragment -> Showing dialog // ${(view as Button).text.toString()}")
                    showDialog()
                } else {
                    Log.d("MainActivity", "CarFragment -> saving car plate // ${(view as Button).text.toString()}")
                    viewModel.saveCarPlate((view as Button).text.toString())
                    carBinding?.carSelectedValue?.text = view.text.toString()
                }
            R.id.car_button_4 ->
                if ((view as Button).text.toString() == "Create" || (view as Button).text.toString() == "create" || (view as Button).text.toString() == "CREATE"){
                    Log.d("MainActivity", "CarFragment -> Showing dialog // ${(view as Button).text.toString()}")
                    showDialog()
                } else {
                    Log.d("MainActivity", "CarFragment -> saving car plate // ${(view as Button).text.toString()}")
                    viewModel.saveCarPlate((view as Button).text.toString())
                    carBinding?.carSelectedValue?.text = view.text.toString()
                }
            R.id.car_button_5 ->
                if ((view as Button).text.toString() == "Create" || (view as Button).text.toString() == "create" || (view as Button).text.toString() == "CREATE"){
                    Log.d("MainActivity", "CarFragment -> Showing dialog // ${(view as Button).text.toString()}")
                    showDialog()
                } else {
                    Log.d("MainActivity", "CarFragment -> saving car plate // ${(view as Button).text.toString()}")
                    viewModel.saveCarPlate((view as Button).text.toString())
                    carBinding?.carSelectedValue?.text = view.text.toString()
                }
        }
    }

    private fun callDeleteCar(car_plate: String){
        if(car_plate != null || car_plate != ""){               // validate the car_plate isnt empty
            Log.d("MainActivity", "deleting the car in vm")
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Confirmation")
                .setMessage("Are you confirmed to delete the car $car_plate?")
                .setNegativeButton("Cancel"){ dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton("Confirm"){ dialog, which ->
                    viewModel.deleteCar(car_plate)
                    dialog.dismiss()
                }
                .show()

        } else {
            Snackbar.make(requireView(), "Empty car plate value", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showDialog(){
        val dialogCarFragment = DialogCarFragment()
        dialogCarFragment.setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
        dialogCarFragment.show(requireFragmentManager(), "Create New Car")
    }

    private fun disablePage(){
        carBinding?.carButton1?.isEnabled = false
        carBinding?.deleteCar1?.isEnabled = false
        carBinding?.carButton2?.isEnabled = false
        carBinding?.deleteCar2?.isEnabled = false
        carBinding?.carButton3?.isEnabled = false
        carBinding?.deleteCar3?.isEnabled = false
        carBinding?.carButton4?.isEnabled = false
        carBinding?.deleteCar4?.isEnabled = false
        carBinding?.carButton5?.isEnabled = false
        carBinding?.deleteCar5?.isEnabled = false
        carBinding?.loginProgress?.loadingProgress?.visibility = View.VISIBLE
    }

    private fun enablePage(){
        carBinding?.carButton1?.isEnabled = true
        carBinding?.deleteCar1?.isEnabled = true
        carBinding?.carButton2?.isEnabled = true
        carBinding?.deleteCar2?.isEnabled = true
        carBinding?.carButton3?.isEnabled = true
        carBinding?.deleteCar3?.isEnabled = true
        carBinding?.carButton4?.isEnabled = true
        carBinding?.deleteCar4?.isEnabled = true
        carBinding?.carButton5?.isEnabled = true
        carBinding?.deleteCar5?.isEnabled = true
        carBinding?.loginProgress?.loadingProgress?.visibility = View.GONE
    }

}
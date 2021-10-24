package com.example.appointmentsystem.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.appointmentsystem.models.Appointment
import com.example.appointmentsystem.room.AppointmentDatabase
import com.example.appointmentsystem.util.SharedPreferenceManager
import kotlinx.coroutines.launch

class HomeViewModel(application: Application):BaseViewModel(application) {
    val isSuccess = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val manager = SharedPreferenceManager.invoke(getApplication())
     val mutableLiveData = MutableLiveData<List<Appointment>>()

     fun getAppointments(userNumber:String){
         isLoading.value = true
        launch {
            try{
                val dao = AppointmentDatabase.invoke(getApplication()).dao()
                val list:List<Appointment> = if(manager.isDoctor()==true){
                    dao.getAppointmentDoctor(userNumber)
                }else{
                    dao.getAppointmentUser(userNumber)
                }
                mutableLiveData.value = list
                isLoading.value = false
                isSuccess.value = true
            }catch (e:Exception){
                isSuccess.value  = false
                isLoading.value= false
            }

        }
    }
}
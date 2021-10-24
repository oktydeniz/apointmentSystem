package com.example.appointmentsystem.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.models.User
import com.example.appointmentsystem.room.AppointmentDatabase
import com.example.appointmentsystem.util.SharedPreferenceManager
import kotlinx.coroutines.launch

class SingInVIewModel(application: Application) : BaseViewModel(application) {
    private val customSharedPreference = SharedPreferenceManager(getApplication())
    val status = MutableLiveData<Boolean>()

    fun getData(doctors: List<Doctor>, users: List<User>) {

        launch {
            val dao = AppointmentDatabase.invoke(getApplication()).dao()
            dao.deleteDoctor()
            dao.insertAll(*doctors.toTypedArray())
            dao.deleteUser()
            dao.insertAll(*users.toTypedArray())

        }
    }

    fun getCurrentUser(isDoctor: Boolean, number: String, password: String) {
        launch {
            status.value = false
            val dao = AppointmentDatabase.invoke(getApplication()).dao()
            if (isDoctor) {
                customSharedPreference.setIsDoctor(true)
                val doctor = dao.selectDoctor(number, password)
                doctor.id?.let { customSharedPreference.saveUser(it) }
                doctor.doctorNumber.let{ customSharedPreference.setUserNumber(it)}
                doctor.fullName.let {
                    if (it != null) {
                        customSharedPreference.setUserName(it)
                    }
                }
                status.value = true
            } else {
                customSharedPreference.setIsDoctor(false)
                val user = dao.selectPerson(number, password)
                println(user)
                user.id?.let { customSharedPreference.saveUser(it) }
                user.userNumber.let{ customSharedPreference.setUserNumber(it)}
                user.fullName.let {
                    if (it != null) {
                        customSharedPreference.setUserName(it)
                    }
                }
                status.value = true
            }
        }

    }
}
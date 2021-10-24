package com.example.appointmentsystem.viewModel

import android.app.Application
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.models.User
import com.example.appointmentsystem.room.AppointmentDatabase
import kotlinx.coroutines.launch

class SingUpViewModel(application: Application) : BaseViewModel(application) {


    fun insertDoctor(doctor: Doctor) {
        launch {
            val dao = AppointmentDatabase.invoke(getApplication()).dao()
            dao.insert(doctor)
        }
    }

    fun insertUser(user: User) {
        launch {
            val dao = AppointmentDatabase.invoke(getApplication()).dao()
            dao.insert(user)
        }
    }
}
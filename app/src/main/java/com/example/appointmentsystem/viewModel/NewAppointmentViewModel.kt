package com.example.appointmentsystem.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.appointmentsystem.models.Appointment
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.room.AppointmentDatabase
import kotlinx.coroutines.launch

class NewAppointmentViewModel(application: Application) : BaseViewModel(application) {
    val doctors = MutableLiveData<List<Doctor>>()
    private val allAppointment = MutableLiveData<List<Appointment>>()
    var status = false

    fun getDoctors(department: String) {
        val dao = AppointmentDatabase.invoke(getApplication()).dao()
        launch {
            doctors.value = dao.getSelectedDepartment(department)
        }
    }

    fun getAll() {
        val dao = AppointmentDatabase.invoke(getApplication()).dao()
        launch {
            allAppointment.value = dao.appointments()
        }

    }

    fun saveNewAppointment(
        department: String,
        currentDoctor: String,
        currentHour: String,
        currentUserNumber: String,
        userName: String,
        doctorName: String,
        message: String
    ) {
        val dao = AppointmentDatabase.invoke(getApplication()).dao()
        allAppointment.value?.let {
            for (i in it) {
                if (i.doctor_id != currentDoctor && i.date != currentHour) {
                    continue

                }
            }
        }
        launch {
            val appointment = Appointment()
            appointment.date = currentHour
            appointment.note = message
            appointment.doctorName = doctorName
            appointment.patientName = userName
            appointment.doctor_id = currentDoctor
            appointment.department = department
            appointment.patient_id = currentUserNumber
            dao.insert(appointment)
        }
    }
}
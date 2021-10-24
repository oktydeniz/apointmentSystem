package com.example.appointmentsystem.fakeData

import android.content.Context
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.util.loadJSONFromAssets
import org.json.JSONArray

class DoctorDataClass {

    companion object {
        fun addDoctors(c: Context): List<Doctor> {
            val doctors = ArrayList<Doctor>()
            val doctorJsonArray = JSONArray(c.loadJSONFromAssets("doctors.json"))
            for (i in 0 until doctorJsonArray.length()) {
                val doctorModel = Doctor()
                val doctorJSONObject = doctorJsonArray.getJSONObject(i)
                doctorModel.department = doctorJSONObject.getString("department")
                doctorModel.email = doctorJSONObject.getString("email")
                doctorModel.phone = doctorJSONObject.getString("phone")
                doctorModel.fullName =
                    doctorJSONObject.getString("firstName") + " " + doctorJSONObject.getString("lastName")
                doctorModel.id = doctorJSONObject.getInt("id")
                doctorModel.doctorNumber = doctorJSONObject.getString("doctorNumber")
                doctorModel.gender = doctorJSONObject.getString("gender")
                doctorModel.password = doctorJSONObject.getString("password")
                doctors.add(doctorModel)
            }
            return doctors
        }
    }
}
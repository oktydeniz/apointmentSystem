package com.example.appointmentsystem.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appointmentsystem.models.Appointment
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.models.User

@Dao
interface DataDao {

    @Insert
    suspend fun insertAll(vararg doctor: Doctor): List<Long>

    @Insert
    suspend fun insertAll(vararg person: User): List<Long>

    @Insert
    suspend fun insert(user: User)

    @Insert
    suspend fun insert(doctor: Doctor)

    @Insert
    suspend fun insert(appointment: Appointment)

    @Insert
    suspend fun insertAppointment(appointment: Appointment)

    @Query("DELETE FROM Appointment WHERE id = :id")
    suspend fun deleteAppointment(id: Int)

    @Query("SELECT fullName FROM Doctor WHERE doctorNumber= :number ")
    suspend fun doctorName(number:String): String

    @Query("DELETE FROM Doctor")
    suspend fun deleteDoctor()

    @Query("DELETE FROM User")
    suspend fun deleteUser()

    @Query("SELECT * FROM Doctor WHERE doctorNumber = :number AND password = :password")
    suspend fun selectDoctor(number: String, password: String): Doctor

    @Query("SELECT * FROM User WHERE userNumber = :number AND  password = :password")
    suspend fun selectPerson(number: String, password: String): User

    @Query("SELECT * FROM Doctor WHERE doctorNumber= :id")
    suspend fun doctorProfile(id:String) : Doctor

    @Query("DELETE FROM APPOINTMENT")
    suspend fun deleteAppointments()

    @Query("SELECT * FROM APPOINTMENT")
    suspend fun appointments() : List<Appointment>

    @Query("SELECT * FROM Doctor WHERE department = :department")
    suspend fun getSelectedDepartment(department:String) : List<Doctor>

    @Query("SELECT * FROM Appointment WHERE patient_id = :userNumber")
    suspend fun getAppointmentUser(userNumber:String) :List<Appointment>

    @Query("SELECT * FROM Appointment WHERE doctor_id = :doctorNumber")
    suspend fun getAppointmentDoctor(doctorNumber:String) :List<Appointment>


}
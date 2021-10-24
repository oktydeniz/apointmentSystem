package com.example.appointmentsystem.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appointmentsystem.models.Appointment
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.models.User


@Database(entities = [Doctor::class, User::class, Appointment::class], version = 1)
abstract class AppointmentDatabase : RoomDatabase() {

    abstract fun dao(): DataDao

    companion object {
        @Volatile
        private var instance: AppointmentDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDB(context).also {
                instance = it
            }
        }

        private fun makeDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppointmentDatabase::class.java,
                "appointment_database"
            ).build()
    }
}
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
        //--> Volatile -> boyle tanimlanmis degerler diger threadlerde gorunur hale getirilir. Coroutines
        //--> gibi yapilar kullandigimiz icin ve bu yapi farkli thread larda cagirilabilecegi icin hepsi icinde
        //--> gorununr hale geliyor (Singleton kullanma amacimizda bu)
        @Volatile
        private var instance: AppointmentDatabase? = null //var mi yokmu kontrol eder
        private val lock = Any()

        //genelde invoke seklinde tanimlanir(baslatmak anlaminda ) instance var ise kullan yok ise olusturmaya yariyor.
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            //synchronized ornegin ayni aynda farkli threadlar bu objeye ulasmak isterse buna izin vermez ve senkron bir sekilde ulasmasini saglar.
            //ayni anda birden fazla threadda islem yapilacak o islem bittikten sonra eger varsa baska thread bir obje ulasmak isterse ulasacak.
            //any degiskeni ise bu synchronized isleminin kilitlenip kilitlenmiyecegibi kontol edicek degisken
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
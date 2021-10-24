package com.example.appointmentsystem.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager {

    companion object {

        private const val PREFERENCE_NAME = "com.example.appointmentsystem"
        private const val PREFERENCE_USERID = "user_id"
        private const val PREFERENCE_FIRST_TIME = "first_time"
        private const val PREFERENCE_IS_DOCTOR = "is_doctor"
        private const val PREFERENCE_USER_NUMBER= "tc_number"
        private const val PREFERENCE_USER_NAME= "user_name"
        private var sharedPreference: SharedPreferences? = null

        @Volatile
        private var instance: SharedPreferenceManager? = null
        private val lock = Any()
        operator fun invoke(context: Context): SharedPreferenceManager =
            instance ?: synchronized(lock) {
                instance ?: makeCustomSharedPreference(context).also {
                    instance = it
                }
            }

        private fun makeCustomSharedPreference(context: Context): SharedPreferenceManager {
            sharedPreference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            return SharedPreferenceManager()
        }

    }

    fun saveUser(userID: Int) {
        sharedPreference?.edit()?.putInt(PREFERENCE_USERID, userID)?.apply()
    }

    fun getUser() = sharedPreference?.getInt(PREFERENCE_USERID, 0)

    fun isFirstTime() = sharedPreference?.getBoolean(PREFERENCE_FIRST_TIME, true)
    fun setFirstTime(status: Boolean) {
        sharedPreference?.edit()?.putBoolean(PREFERENCE_FIRST_TIME, status)?.apply()
    }

    fun getUserNumber() = sharedPreference?.getString(PREFERENCE_USER_NUMBER, "0")
    fun setUserNumber(number:String) {
        sharedPreference?.edit()?.putString(PREFERENCE_USER_NUMBER, number)?.apply()
    }
    fun getUserName() = sharedPreference?.getString(PREFERENCE_USER_NAME, "null")
    fun setUserName(name:String) {
        sharedPreference?.edit()?.putString(PREFERENCE_USER_NAME, name)?.apply()
    }

    fun isDoctor() = sharedPreference?.getBoolean(PREFERENCE_IS_DOCTOR, false)
    fun setIsDoctor(status: Boolean) {
        sharedPreference?.edit()?.putBoolean(PREFERENCE_IS_DOCTOR, status)?.apply()
    }
}
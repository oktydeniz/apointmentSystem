package com.example.appointmentsystem.fakeData

import android.content.Context
import com.example.appointmentsystem.models.User
import com.example.appointmentsystem.util.loadJSONFromAssets
import org.json.JSONArray

class UserDataClass {

    companion object {
        fun addUsers(c: Context): List<User> {
            val user = ArrayList<User>()
            val doctorJsonArray = JSONArray(c.loadJSONFromAssets("users.json"))
            for (i in 0 until doctorJsonArray.length()) {
                val userModel = User()
                val userJSONObject = doctorJsonArray.getJSONObject(i)
                userModel.email = userJSONObject.getString("email")
                userModel.phone = userJSONObject.getString("phone")
                userModel.fullName =
                    userJSONObject.getString("firstName") + " " + userJSONObject.getString("lastName")
                userModel.id = userJSONObject.getInt("id")
                userModel.gender = userJSONObject.getString("gender")
                userModel.userNumber = userJSONObject.getString("userNumber")
                userModel.password = userJSONObject.getString("password")
                user.add(userModel)
            }
            return user
        }
    }
}
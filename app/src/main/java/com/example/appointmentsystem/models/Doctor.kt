package com.example.appointmentsystem.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = false)
    var id: Int?,
    @ColumnInfo
    var fullName: String?,
    @ColumnInfo
    var email: String?,
    @ColumnInfo
    var phone: String?,
    @ColumnInfo
    var gender: String?,
    @ColumnInfo
    var department: String?,
    @ColumnInfo
    var password: String,
    @ColumnInfo
    var doctorNumber: String
) : Serializable {
    constructor() : this(null, "", "", "", "", "", "", "")
}
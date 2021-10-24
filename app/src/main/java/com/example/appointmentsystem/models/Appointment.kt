package com.example.appointmentsystem.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo
    var date: String,
    @ColumnInfo
    var doctor_id: String,
    @ColumnInfo
    var patient_id: String,
    @ColumnInfo
    var doctorName: String,
    @ColumnInfo
    var patientName: String,
    @ColumnInfo
    var department: String,
    @ColumnInfo
    var note: String?,
){
    constructor() : this(null, "","","", "", "", "", "")
}

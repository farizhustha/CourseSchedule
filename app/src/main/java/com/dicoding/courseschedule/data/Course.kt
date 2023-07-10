package com.dicoding.courseschedule.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.courseschedule.data.DataCourseName.TABLE_NAME

//TODO 1 : Define a local database table using the schema in app/schema/course.json

@Entity(tableName = TABLE_NAME)
data class Course(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val courseName: String = "",
    val day: Int = 0,
    val startTime: String = "",
    val endTime: String = "",
    val lecturer: String = "",
    val note: String = ""
)

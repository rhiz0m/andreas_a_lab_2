package com.rhiz0me.myapplication.model

import java.time.LocalDate
import java.time.LocalTime

data class Date (
    var date: LocalDate,
    var time: LocalTime,
        ) {
    override fun toString(): String {
        return "Todays date: $date, Local time: $time"
    }
}
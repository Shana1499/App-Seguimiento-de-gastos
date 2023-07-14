package com.example.appseguimientogastos.data.model

import android.os.Build
import java.time.LocalDate
import java.util.Calendar

data class Month(
    val name: String
)

val month: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    val current = LocalDate.now()
    current.monthValue
} else {
    val calendar = Calendar.getInstance()
    calendar.get(Calendar.MONTH) + 1 // Calendar months are 0-indexed
}

fun getCurrentMonth(): Month {

    return monthList[month - 1]
}

fun getPreviouMonth(): Month {
    val current = month - 1
    if (current < 0) {
        return monthList.last()

    } else {
        return monthList[current - 1]
    }
}


val monthList = listOf(
    Month("Enero"),
    Month("Febrero"),
    Month("Marzo"),
    Month("Abril"),
    Month("Mayo"),
    Month("Junio"),
    Month("Julio"),
    Month("Agosto"),
    Month("Septiembre"),
    Month("Octubre"),
    Month("Noviembre"),
    Month("Diciembre")
)

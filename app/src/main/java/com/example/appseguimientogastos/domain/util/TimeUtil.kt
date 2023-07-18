package com.example.appseguimientogastos.domain.util

import java.text.SimpleDateFormat
import java.util.Date


 class TimeUtil {
     companion object {
         fun getTimeInMillisSinceEpoch(): Long = System.currentTimeMillis()
         fun getReadableDateFromEpoch(timeEpoch: Long): String? {
            try {
                val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSZ")
                val netDate = Date(timeEpoch)
                return sdf.format(netDate)
            } catch (_: Throwable) {
            }
            return null
        }
    }
}

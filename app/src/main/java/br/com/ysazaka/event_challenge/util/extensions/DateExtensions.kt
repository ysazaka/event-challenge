package br.com.ysazaka.event_challenge.util.extensions

import android.text.format.DateFormat
import br.com.ysazaka.event_challenge.util.Constants.Companion.LOCALE_BRAZIL
import java.util.*
import kotlin.math.roundToLong


/**
 * Created by Glauco Sazaka on 02/08/2021.
 */
fun Double.getDate(): String {
    val dateMask = "dd/MM/yy"

    val calendar: Calendar = Calendar.getInstance(LOCALE_BRAZIL)
    calendar.timeInMillis = (this * 1000).roundToLong()
    return DateFormat.format(dateMask, calendar).toString()
}
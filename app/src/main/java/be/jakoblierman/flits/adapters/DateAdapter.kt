package be.jakoblierman.flits.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateAdapter {

    private var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.getDefault())

    @ToJson
    internal fun toJson(date: Date): String {
        return format.format(date)
    }

    @FromJson
    internal fun fromJson(json: String): Date? {
        return try {
            format.parse(json)
        } catch (e: ParseException) {
            null
        }
    }

}

package cz.pekostudio.datetimepickers

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.format.DateFormat
import net.danlew.android.joda.JodaTimeAndroid
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime
import java.util.*

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

class DateTimePicker(
    private var context: Context,
    private var dateTime: DateTime? = null,
    public var config: Config = Config()
) {

    init {
        if (dateTime == null) dateTime = DateTime.now()

        JodaTimeAndroid.init(context)
        dateTime = dateTime!!.withZone(DateTimeZone.forTimeZone(TimeZone.getDefault()))
    }

    public fun showTimePicker(
        onPick: (dateTime: DateTime) -> Unit
    ) {
        TimePickerDialog(context, { _, hours, minutes ->
            onPick.invoke(
                dateTime
                    !!.withHourOfDay(hours)
                    .withMinuteOfHour(minutes)
            )
        },
            dateTime!!.hourOfDay,
            dateTime!!.minuteOfHour,
            config.hoursMode.is24 ?: DateFormat.is24HourFormat(context)
        ).show()
    }

    public fun showDatePicker(
        onPick: (dateTime: DateTime) -> Unit
    ) {
        DatePickerDialog(context, { _, year, month, dayOfMonth ->
            onPick.invoke(
                dateTime
                    !!.withYear(year)
                    .withMonthOfYear(month + 1)
                    .withDayOfMonth(dayOfMonth)
            )
        },
            dateTime!!.year,
            dateTime!!.monthOfYear.minus(1),
            dateTime!!.dayOfMonth
        ).show()
    }

    public fun showDateTimePicker(
        onPick: (dateTime: DateTime) -> Unit
    ) {
        showDatePicker { date ->
            DateTimePicker(context, date, config).showTimePicker { time ->
                onPick(time)
            }
        }
    }

    enum class HoursMode(val is24: Boolean?) {
        FORMAT_24(true),
        FORMAT_12(false),
        FORMAT_AUTO(null)
    }

    public class Config {
        public var hoursMode = HoursMode.FORMAT_AUTO
    }
}
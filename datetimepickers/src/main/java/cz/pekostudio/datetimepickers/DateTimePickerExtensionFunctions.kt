package cz.pekostudio.datetimepickers

import android.content.Context
import org.joda.time.DateTime

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

fun DateTime?.showTimePicker(
    context: Context,
    onPick: (dateTime: DateTime) -> Unit
) {
    DateTimePicker(context, this).showTimePicker {
        onPick.invoke(it)
    }
}

fun DateTime?.showDatePicker(
    context: Context,
    onPick: (dateTime: DateTime) -> Unit
) {
    DateTimePicker(context, this).showDatePicker {
        onPick.invoke(it)
    }
}

fun DateTime?.showDateTimePicker(
    context: Context,
    onPick: (dateTime: DateTime) -> Unit
) {
    DateTimePicker(context, this).showDateTimePicker {
        onPick.invoke(it)
    }
}
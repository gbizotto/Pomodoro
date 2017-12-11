package br.gbizotto.pomodoro.history.viewModel

import android.content.Context
import android.databinding.BaseObservable
import br.gbizotto.pomodoro.R
import br.gbizotto.pomodoro.model.Pomodoro
import br.gbizotto.pomodoro.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Gabriela on 10/12/2017.
 */
class HistoryItemViewModel(var pomodoro: Pomodoro, var context: Context) : BaseObservable() {

    var isHeader: Boolean = false
    var headerDate: Date? = null

    fun getDateAddedLabel(): String {
        val dateFormat = SimpleDateFormat(context.getString(R.string.date_format))
        if (headerDate == null) {
            return dateFormat.format(pomodoro.dateAdded)
        }
        if (DateUtils.isToday(headerDate)) {
            return context.getString(R.string.today)
        }
        if (DateUtils.isYesterday(headerDate)) {
            return context.getString(R.string.yesterday)
        }
        return dateFormat.format(headerDate)
    }

    fun getStatusLabel(): String {
        if (pomodoro.finished) {
            return context.getString(R.string.status_finished)
        }
        return context.getString(R.string.status_stopped)
    }

    fun getDuration(): String {
        return "${DateUtils.getMinutes(pomodoro.duration)}:${DateUtils.getSeconds(pomodoro.duration)}"
    }

    fun getEndTime() : String {
        val dateFormat = SimpleDateFormat(context.getString(R.string.time_format))
        return dateFormat.format(pomodoro.dateAdded)
    }

    fun setEndTime(endTime: String) {

    }

    fun setDateAddedLabel(dateAddedd: String) {

    }

    fun setStatusLabel(status: String) {

    }

    fun setDuration(duration: String) {

    }
}
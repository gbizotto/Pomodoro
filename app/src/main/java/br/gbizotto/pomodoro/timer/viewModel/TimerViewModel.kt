package br.gbizotto.pomodoro.timer.viewModel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.os.CountDownTimer
import android.util.Log
import br.gbizotto.pomodoro.model.Pomodoro
import br.gbizotto.pomodoro.repositories.PomodoroRepository

/**
 * Created by Gabriela on 10/12/2017.
 */

class TimerViewModel : BaseObservable() {

    val maxTime = (2 * 60 * 1000).toLong()

    var timer: ObservableField<String> = ObservableField(getDefaultTime())
    var isRunning: ObservableField<Boolean> = ObservableField(false)

    private var countDownTimer: CountDownTimer? = null
    private var millisecondsLeft: Long = 0

    fun onPlayStopClick() {
        Log.d(TimerViewModel::class.java.simpleName, "apertou no botão")

        if (isRunning.get()) {
            stopClock()
        } else {
            startClock()
        }
    }

    private fun getDefaultTime() : String {
        return "${getMinutes(maxTime)}:${getSeconds(maxTime)}"
    }

    private fun startClock() {
        countDownTimer = object : CountDownTimer(maxTime, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer.set("${getMinutes(millisUntilFinished)}:${getSeconds(millisUntilFinished)}")
                millisecondsLeft = millisUntilFinished
            }

            override fun onFinish() {
                savePomodoro(0, true)
                timer.set(getDefaultTime())
            }
        }.start()

        isRunning.set(true)
    }

    private fun savePomodoro(milliseconds: Long, finished: Boolean) {
        var pomodoro = Pomodoro()
        pomodoro.duration = maxTime - milliseconds
        pomodoro.finished = finished

        PomodoroRepository.insert(pomodoro)
    }

    private fun getMinutes(milliseconds: Long): String {
        var seconds = milliseconds / 1000
        var minutes = (seconds / 60).toInt()
        return format(minutes)
    }

    private fun getSeconds(milliseconds: Long) : String {
        var seconds = milliseconds / 1000
        return format(((seconds % 60)).toInt())
    }

    private fun format(time: Int) : String {
        if (time>= 10) {
            return time.toString()
        }
        return "0$time"
    }

    private fun stopClock() {
        savePomodoro(millisecondsLeft, false)
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
        isRunning.set(false)
    }

}

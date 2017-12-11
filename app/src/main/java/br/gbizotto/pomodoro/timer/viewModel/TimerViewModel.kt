package br.gbizotto.pomodoro.timer.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.os.CountDownTimer
import br.gbizotto.pomodoro.model.Pomodoro
import br.gbizotto.pomodoro.repositories.PomodoroRepository
import br.gbizotto.pomodoro.utils.DateUtils
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator


/**
 * Created by Gabriela on 10/12/2017.
 */

class TimerViewModel(var context: Context) : BaseObservable() {

    val maxTime = (1 * 60 * 1000).toLong()

    var timer: ObservableField<String> = ObservableField(getDefaultTime())
    var isRunning: ObservableField<Boolean> = ObservableField(false)

    private var countDownTimer: CountDownTimer? = null
    private var millisecondsLeft: Long = 0

    fun onPlayStopClick() {
        if (isRunning.get()) {
            stopClock()
        } else {
            startClock()
        }
    }

    private fun getDefaultTime() : String {
        return "${DateUtils.getMinutes(maxTime)}:${DateUtils.getSeconds(maxTime)}"
    }

    private fun startClock() {
        countDownTimer = object : CountDownTimer(maxTime, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer.set("${DateUtils.getMinutes(millisUntilFinished)}:${DateUtils.getSeconds(millisUntilFinished)}")
                millisecondsLeft = millisUntilFinished
            }

            override fun onFinish() {
                finishTimer()
            }
        }.start()

        isRunning.set(true)
    }

    private fun finishTimer() {
        vibrate()
        savePomodoro(0, true)
        timer.set(getDefaultTime())
    }

    private fun vibrate() {
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(400, 1))
        } else {
            v.vibrate(400)
        }
    }

    private fun savePomodoro(milliseconds: Long, finished: Boolean) {
        var pomodoro = Pomodoro()
        pomodoro.duration = maxTime - milliseconds
        pomodoro.finished = finished

        PomodoroRepository.insert(pomodoro)
    }

    private fun stopClock() {
        savePomodoro(millisecondsLeft, false)
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
        isRunning.set(false)
    }

}

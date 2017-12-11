package br.gbizotto.pomodoro.timer.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.gbizotto.pomodoro.R
import br.gbizotto.pomodoro.databinding.FragmentTimerBinding
import br.gbizotto.pomodoro.timer.viewModel.TimerViewModel

class TimerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentTimerBinding = DataBindingUtil.inflate(inflater ,R.layout.fragment_timer,container , false)
        binding.viewModel = TimerViewModel(context)
        return binding.root
    }
}
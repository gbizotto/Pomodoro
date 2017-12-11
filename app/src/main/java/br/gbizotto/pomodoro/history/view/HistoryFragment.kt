package br.gbizotto.pomodoro.history.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.gbizotto.pomodoro.R
import br.gbizotto.pomodoro.databinding.FragmentHistoryBinding
import br.gbizotto.pomodoro.history.adapter.HistoryAdapter
import br.gbizotto.pomodoro.history.viewModel.HistoryItemViewModel
import br.gbizotto.pomodoro.history.viewModel.HistoryViewModel
import br.gbizotto.pomodoro.model.Pomodoro
import br.gbizotto.pomodoro.repositories.PomodoroRepository
import br.gbizotto.pomodoro.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*

class HistoryFragment : Fragment() {

    var viewModel : HistoryViewModel = HistoryViewModel()
    var adapter : HistoryAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history,container , false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        listPomodoros()
    }

    private fun initRecyclerView() {
        history_list.setLayoutManager(LinearLayoutManager(context))
        txt_empty_history.setVisibility(View.VISIBLE)
    }

    private fun listPomodoros(){
        var pomodoros = PomodoroRepository.list()
        var history = mutableListOf<HistoryItemViewModel>()

        var lastDate : Date? = null

        for(pomodoro in pomodoros) {
            Log.d("pomodoro", pomodoro.toString())

            if (lastDate == null || !DateUtils.isSameDate(pomodoro.dateAdded, lastDate)) {
                history.add(getHeader(pomodoro.dateAdded))
            }

            history.add(HistoryItemViewModel(pomodoro, context))

            lastDate = pomodoro.dateAdded
        }

        val historyList = history.toList()
        viewModel.history = historyList

        if (!history.isEmpty()) {
            if (adapter == null) {
                adapter = HistoryAdapter(historyList, context)
            } else {
                adapter?.setHistory(historyList)
            }

            history_list.adapter = adapter

            txt_empty_history.setVisibility(View.GONE)
            history_list.setVisibility(View.VISIBLE)
        }
    }

    private fun getHeader(date: Date) : HistoryItemViewModel {
        var header = HistoryItemViewModel(Pomodoro(), context)
        header.headerDate = date
        header.isHeader = true
        return header
    }
}
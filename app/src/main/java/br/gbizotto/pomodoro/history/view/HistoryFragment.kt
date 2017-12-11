package br.gbizotto.pomodoro.history.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import br.gbizotto.pomodoro.R
import br.gbizotto.pomodoro.databinding.FragmentHistoryBinding
import br.gbizotto.pomodoro.history.viewModel.HistoryViewModel
import br.gbizotto.pomodoro.repositories.PomodoroRepository
import butterknife.BindView
import butterknife.ButterKnife

class HistoryFragment : Fragment() {

    @BindView(R.id.history_list)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.txt_empty_history)
    lateinit var emptyList: TextView

    var viewModel : HistoryViewModel = HistoryViewModel()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history,container , false)
        binding.viewModel = viewModel

        ButterKnife.bind(context, binding.root)

        initRecyclerView()
        listPomodoros()

        return binding.root
    }

    private fun initRecyclerView() {
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        emptyList.setVisibility(View.VISIBLE)
    }

    private fun listPomodoros(){
        viewModel.history = PomodoroRepository.list()
    }
}
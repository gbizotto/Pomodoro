package br.gbizotto.pomodoro.history.adapter

/**
 * Created by Gabriela on 10/12/2017.
 */

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.gbizotto.pomodoro.R
import br.gbizotto.pomodoro.databinding.HistoryItemRowBinding
import br.gbizotto.pomodoro.history.viewModel.HistoryItemViewModel

class HistoryAdapter(private var history: List<HistoryItemViewModel>?, private val context: Context) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<HistoryItemRowBinding>(layoutInflater, R.layout.history_item_row, parent, false)
        return HistoryAdapter.ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(history!![position])
    }

    override fun getItemCount(): Int {
        return if (history == null || history!!.isEmpty()) {
            0
        } else history!!.size
    }

    fun setHistory(history: List<HistoryItemViewModel>?) {
        this.history = history
    }

    class ViewHolder(itemView: View, private val binding: HistoryItemRowBinding) : RecyclerView.ViewHolder(itemView) {

        fun bindProduct(productViewModel: HistoryItemViewModel) {
            binding.viewModel = productViewModel
            binding.executePendingBindings()
        }
    }
}

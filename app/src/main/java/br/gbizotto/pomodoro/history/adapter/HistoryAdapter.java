package br.gbizotto.pomodoro.history.adapter;

/**
 * Created by Gabriela on 10/12/2017.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.gbizotto.pomodoro.R;
import br.gbizotto.pomodoro.databinding.HistoryItemRowBinding;
import br.gbizotto.pomodoro.history.viewModel.HistoryItemViewModel;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryItemViewModel> history;
    private Context context;

    public HistoryAdapter(List<HistoryItemViewModel> history, Context context) {
        this.history = history;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        HistoryItemRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.history_item_row, parent, false);
        return new HistoryAdapter.ViewHolder(binding.getRoot(),binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindProduct(history.get(position));
    }

    @Override
    public int getItemCount() {
        if (history == null || history.isEmpty()) {
            return 0;
        }
        return history.size();
    }

    public void setHistory(List<HistoryItemViewModel> products) {
        this.history = products;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private HistoryItemRowBinding binding;

        public ViewHolder(View itemView, HistoryItemRowBinding historyItemRowBinding) {
            super(itemView);
            binding = historyItemRowBinding;
        }

        public void bindProduct(HistoryItemViewModel productViewModel) {
            binding.setViewModel(productViewModel);
            binding.executePendingBindings();
        }
    }
}

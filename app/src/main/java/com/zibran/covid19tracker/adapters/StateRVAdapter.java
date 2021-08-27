package com.zibran.covid19tracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zibran.covid19tracker.R;
import com.zibran.covid19tracker.models.StateModel;

import java.util.List;

public class StateRVAdapter extends RecyclerView.Adapter<StateRVAdapter.ViewHolder> {
    List<StateModel> stateModelList;
    Context context;
    public StateRVAdapter(List<StateModel> stateModelList, Context context) {
        this.stateModelList = stateModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateRVAdapter.ViewHolder holder, int position) {

        holder.states.setText(stateModelList.get(position).getState());
        holder.cases.setText(" " + stateModelList.get(position).getCases());
        holder.recovered.setText(" " + stateModelList.get(position).getRecovered());
        holder.deaths.setText(" " + stateModelList.get(position).getDeaths());
    }

    @Override
    public int getItemCount() {
        return stateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView states, cases, recovered, deaths;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            states = itemView.findViewById(R.id.states);
            cases = itemView.findViewById(R.id.number_of_cases);
            recovered = itemView.findViewById(R.id.number_of_recovered);
            deaths = itemView.findViewById(R.id.number_of_death);
        }
    }
}

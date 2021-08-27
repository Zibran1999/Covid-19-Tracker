package com.zibran.covid19tracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zibran.covid19tracker.R;
import com.zibran.covid19tracker.models.CenterRVModel;

import java.util.List;

public class CenterRVAdapter extends RecyclerView.Adapter<CenterRVAdapter.ViewHolder> {
    List<CenterRVModel> centerRVModelList;
    Context context;

    public CenterRVAdapter(List<CenterRVModel> centerRVModelList, Context context) {
        this.centerRVModelList = centerRVModelList;
        this.context = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.center_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CenterRVAdapter.ViewHolder holder, int position) {
    holder.centerNameTV.setText(centerRVModelList.get(position).getCenterName());
    holder.centerAddressTV.setText(centerRVModelList.get(position).getCenterAddress());
    holder.centerTimingTv.setText("From : "+centerRVModelList.get(position).getCenterFromTime()+" To : "+centerRVModelList.get(position).getCenterToTime());
    holder.vaccineNameTV.setText(centerRVModelList.get(position).getVaccineName());
    holder.vaccineFeesTV.setText(centerRVModelList.get(position).getFeeType());
    holder.ageLimitTV.setText("Age Limit : "+centerRVModelList.get(position).getAgeLimit());
    holder.avalabilityTV.setText("Availability : "+centerRVModelList.get(position).getAvailabilityCapacity());
    }

    @Override
    public int getItemCount() {
        return centerRVModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView centerNameTV,centerAddressTV,centerTimingTv,vaccineNameTV,vaccineFeesTV,ageLimitTV,avalabilityTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            centerNameTV = itemView.findViewById(R.id.idTVCeterName);
            centerAddressTV = itemView.findViewById(R.id.idTVCeterLocation);
            centerTimingTv = itemView.findViewById(R.id.idTVCenterTimings);
            vaccineNameTV = itemView.findViewById(R.id.idTVVaccineName);
            vaccineFeesTV = itemView.findViewById(R.id.idTVVaccineFees);
            ageLimitTV = itemView.findViewById(R.id.idTVAgeLimit);
            avalabilityTV = itemView.findViewById(R.id.idTVAvailability);
        }
    }
}

package com.zibran.covid19tracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.zibran.covid19tracker.AffectedCountries;
import com.zibran.covid19tracker.R;
import com.zibran.covid19tracker.models.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class MyCustumAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelsListFiltered;


    public MyCustumAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_item,countryModelList);
        this.context=context;
        this.countryModelList=countryModelList;
        this.countryModelsListFiltered=countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);

        TextView tvCountryName=view.findViewById(R.id.tvCountryName);
        ImageView imageView=view.findViewById(R.id.imageFlag);

        tvCountryName.setText(countryModelsListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelsListFiltered.get(position).getFlag()).into(imageView);


        return view;
    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }



    @Nullable
    @Override
    public CountryModel getItem(int position) {

        return countryModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    public void getFilters(String s){
//        String searchStr = s.toString().toLowerCase();
//        List<CountryModel> countryModels = new ArrayList<>();
//        for (CountryModel c: countryModelList){
//
//            if (c.getCountry().toLowerCase().contains(searchStr)){
//                countryModels.add(c);
//                notifyDataSetChanged();
//            }
//        }
//
//    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;

                }else{
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryModel itemsModel : countryModelList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelsListFiltered = (List<CountryModel>) results.values;
                AffectedCountries.countryModelsList = (List<CountryModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}

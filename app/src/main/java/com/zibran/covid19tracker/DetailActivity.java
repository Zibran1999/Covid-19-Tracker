package com.zibran.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    TextView tvCountry,tvCases , tvRecovered , tvCritical , tvActive ,tvTodayCases , tvTotalDeaths , tvTodayDeaths;
    Button stateWiseBtn,findVaccinationCenterBtn;
    String country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Intent intent=getIntent();
        int positionCountry = intent.getIntExtra("position", 0);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Details of "+AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvCountry=findViewById(R.id.tvCountry);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvTodayCases=findViewById(R.id.tvTodayCases);
        tvTotalDeaths=findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);
        stateWiseBtn =findViewById(R.id.state_wise_cases_btn);
        findVaccinationCenterBtn= findViewById(R.id.find_vaccination_center_btn);

        country = AffectedCountries.countryModelsList.get(positionCountry).getCountry();
        if (country.equals("India")){
            stateWiseBtn.setVisibility(View.VISIBLE);
            findVaccinationCenterBtn.setVisibility(View.VISIBLE);
        }

        tvCountry.setText(AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getCases());
        tvRecovered.setText(AffectedCountries.countryModelsList.get(positionCountry).getRecoverd());
        tvCritical.setText(AffectedCountries.countryModelsList.get(positionCountry).getCritical());
        tvActive.setText(AffectedCountries.countryModelsList.get(positionCountry).getActive());
        tvTodayCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayDeaths());
        stateWiseBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),StateRVActivity.class)));
        findVaccinationCenterBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),FindVaccinationCenterActivity.class)));








    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

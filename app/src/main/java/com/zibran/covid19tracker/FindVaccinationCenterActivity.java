package com.zibran.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zibran.covid19tracker.adapters.CenterRVAdapter;
import com.zibran.covid19tracker.models.CenterRVModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FindVaccinationCenterActivity extends AppCompatActivity {

    private EditText pinCodeEdt;
    private RecyclerView centerRV;
    private ProgressBar loadingBar;
    private List<CenterRVModel> centerRVModelList;
    private CenterRVAdapter centerRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_find_vaccination_center);
        pinCodeEdt = findViewById(R.id.idEditPinCode);
        Button searchBtn = findViewById(R.id.idBtnSearch);
        centerRV = findViewById(R.id.idRV);
        loadingBar = findViewById(R.id.idLoadingBar);
        centerRVModelList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        centerRV.setLayoutManager(layoutManager);
        centerRVAdapter = new CenterRVAdapter(centerRVModelList, this);
        centerRV.setAdapter(centerRVAdapter);

        searchBtn.setOnClickListener(view -> {
            String pinCode = pinCodeEdt.getText().toString();
            if (pinCode.length() != 6) {
                Toast.makeText(this, "Please Enter valid pin code", Toast.LENGTH_SHORT).show();
            } else {
                centerRVModelList.clear();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        loadingBar.setVisibility(View.VISIBLE);
                        String dateStr = "" + dayOfMonth + "-" + (month + 1) + "-" + year;
                        getAppointmentDetails(pinCode, dateStr);
                    }
                }, year, month, day);
                pickerDialog.show();
            }
        });

    }

    private void getAppointmentDetails(String pinCode, String dateStr) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pinCode + "&date=" + dateStr, null, response -> {
            loadingBar.setVisibility(View.GONE);
            try {
                JSONArray jsonArray = response.getJSONArray("centers");
                if (jsonArray.length() == 0) {
                    Toast.makeText(this, "No Vaccination center available", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String centerName = jsonObject.getString("name");
                    String centerAddress = jsonObject.getString("address");
                    String centerFromTime = jsonObject.getString("from");
                    String centerToTime = jsonObject.getString("to");
                    String feeType = jsonObject.getString("fee_type");
                    JSONObject sessionObj = jsonObject.getJSONArray("sessions").getJSONObject(0);
                    int availabilityCapacity = sessionObj.getInt("available_capacity");
                    int ageLimit = sessionObj.getInt("min_age_limit");
                    String vaccineName = sessionObj.getString("vaccine");
                    centerRVModelList.add(new CenterRVModel(centerName, centerAddress, centerFromTime, centerToTime, feeType, ageLimit, vaccineName, availabilityCapacity));
                }

                centerRVAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
                loadingBar.setVisibility(View.GONE);
            }


        }, error -> {
            Toast.makeText(this, "Failed to get data", Toast.LENGTH_SHORT).show();
            loadingBar.setVisibility(View.GONE);
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
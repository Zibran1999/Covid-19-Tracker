package com.zibran.covid19tracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zibran.covid19tracker.adapters.StateRVAdapter;
import com.zibran.covid19tracker.models.StateModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StateRVActivity extends AppCompatActivity {

    List<StateModel> stateModelList;
    StateRVAdapter stateRVAdapter;
    RecyclerView recyclerView;
    Dialog stateDialog;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_rvactivity);

        recyclerView = findViewById(R.id.state_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        stateModelList = new ArrayList<>();
        stateDialog = new Dialog(this);
        stateDialog.setContentView(R.layout.loading_dialog);
        stateDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            stateDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.progress_bg));
        }

        stateDialog.setCancelable(false);


        fetchData();
    }

    private void fetchData() {
        stateDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://api.rootnet.in/covid19-in/stats/latest", null, response -> {
            Log.e("ddeeeeeeee", response.toString());

            try {
                JSONObject jsonObject;
                jsonObject = response.getJSONObject("data");

                JSONArray jsonArray = jsonObject.getJSONArray("regional");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String state = object.getString("loc");
                    int cases = object.getInt("totalConfirmed");
                    int recovered = object.getInt("discharged");
                    int deaths = object.getInt("deaths");
                    stateModelList.add(new StateModel(state, recovered, deaths, cases));
                    Log.e("ddddd", stateModelList.toString());
                }
                stateDialog.dismiss();
                stateRVAdapter = new StateRVAdapter(stateModelList, this);
                recyclerView.setAdapter(stateRVAdapter);
                stateRVAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
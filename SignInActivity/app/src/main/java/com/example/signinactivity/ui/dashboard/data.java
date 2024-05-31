package com.example.signinactivity.ui.dashboard;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Switch;

import com.example.signinactivity.R;
import com.example.signinactivity.databinding.FragmentDashboardBinding;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class data extends AsyncTask<Void, Void, String>{
    private FragmentDashboardBinding binding;
    private Switch switchContactor1;
    private Switch switchContactor2;
    private Switch switchContactorOff1;
    private Switch switchContactorOff2;

    public data(FragmentDashboardBinding binding) {
        this.binding = binding;
    }

    @Override
    protected String doInBackground(Void... voids){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.31.90/data")
                .build();

        String data = null;

        try {
            Response response = client.newCall(request).execute();
            data = response.body().string();
            Log.d("MyApp", "Received data: " + data);
        } catch (IOException e) {
            Log.d("ERROR", "Error is: " + e);
        }

        return data;
    }

    @Override
    protected void onPostExecute(String data) {
        this.switchContactor1 = binding.getRoot().findViewById(R.id.switch_contactor_1);
        this.switchContactor2 = binding.getRoot().findViewById(R.id.switch_contactor_2);
        this.switchContactorOff1 = binding.getRoot().findViewById(R.id.switch_contactor_1_off);
        this.switchContactorOff2 = binding.getRoot().findViewById(R.id.switch_contactor_2_off);

        if (data != null) {
            if (data.substring(0, 2).equals("11")) {
                switchContactor1.setChecked(true);
                switchContactorOff1.setChecked(false);
            } else if (data.substring(0, 2).equals("00")){
                switchContactorOff1.setChecked(true);
                switchContactor1.setChecked(false);
            } else {
                switchContactor1.setChecked(false);
                switchContactorOff1.setChecked(false);
            }

            if (data.substring(2, 4).equals("11")) {
                switchContactor2.setChecked(true);
                switchContactorOff2.setChecked(false);
            } else if (data.substring(2, 4).equals("00")){
                switchContactorOff2.setChecked(true);
                switchContactor2.setChecked(false);
            } else {
                switchContactor2.setChecked(false);
                switchContactorOff2.setChecked(false);
            }

            /*if (data.substring(0, 2).equals("11")) {
                switchContactor1.setChecked(true);
            } else if (data.substring(0, 2).equals("10")){
                switchContactor1.setChecked(false);
            } else {
                switchContactorOff1.setChecked(true);
            }
            if (data.substring(2, 4).equals("11")) {
                switchContactor2.setChecked(true);
            } else if (data.substring(2, 4).equals("10")){
                switchContactor2.setChecked(false);
            } else {
                switchContactorOff2.setChecked(true);
            }*/
        } else {
            Log.d("ERROR", "Data is null");
        }
    }
}

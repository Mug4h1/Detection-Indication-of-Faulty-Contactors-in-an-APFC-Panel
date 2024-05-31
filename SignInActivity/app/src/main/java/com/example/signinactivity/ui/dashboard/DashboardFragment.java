package com.example.signinactivity.ui.dashboard;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.signinactivity.R;
import com.example.signinactivity.databinding.FragmentDashboardBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends Fragment {

    private static final String NOTIFICATION_CHANNEL_ID = "my_channel_id";
    private static final int NOTIFICATION_ID = 123;

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        while(true) {
            new data(binding).execute();

            // Get the data from the IP address
            //String ipAddress = "192.168.1.100"; // replace with your own IP address
            //String data = getDataFromIpAddress(ipAddress);

            //String data = "01";

            final TextView textView = binding.textContactor;
            dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
            return root;
        }

    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon_foreground)
                .setContentTitle("Toggle is OFF")
                .setContentText("One of the toggle switches is in the OFF state.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "My channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private String getDataFromIpAddress(String ipAddress) {
        String data = "00";
        try {
            URL url = new URL("http://" + ipAddress + "/data"); // replace with your own URL
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            data = in.readLine();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

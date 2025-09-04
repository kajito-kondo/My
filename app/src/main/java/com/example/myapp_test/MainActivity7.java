package com.example.myapp_test;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp_test.databinding.ActivityMain7Binding;

import java.util.stream.Collectors;

public class MainActivity7 extends AppCompatActivity {
    private ActivityMain7Binding binding;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectivityManager = getSystemService(ConnectivityManager.class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);
    }

    private void updateTransport(Network currentNetwork) {
        var caps = connectivityManager.getNetworkCapabilities(currentNetwork);
        if (caps != null) {
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "WI-FI";
            } else {
                transport = "その他";
            }
            binding.textView8.setText(transport);
        }
    }

    private void updateIpAddress(Network currentNetwork) {
        var linkProperties = connectivityManager.getLinkProperties(currentNetwork);
        if (linkProperties != null) {
            var addresses = linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.textView7.setText(addresses);
        }
    }
    private void registerNetworkCallback() {
        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                runOnUiThread(() -> {
                    updateTransport(network);
                    updateIpAddress(network);
                });
            }
        });
    }
}
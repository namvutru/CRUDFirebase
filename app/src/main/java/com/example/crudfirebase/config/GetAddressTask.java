package com.example.crudfirebase.config;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.crudfirebase.activity.AccountActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetAddressTask extends AsyncTask<Double, Void, String> {
    private AccountActivity activity; // Thay thế "MainActivity" bằng tên lớp hoặc hoạt động của bạn

    public GetAddressTask(AccountActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Double... params) {
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        double latitude = params[0];
        double longitude = params[1];

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                StringBuilder addressBuilder = new StringBuilder();

                // Lấy thông tin địa chỉ từ các thành phần
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressBuilder.append(address.getAddressLine(i)).append("\n");
                }

                return addressBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(String address) {
        if (address != null) {
            // Ở đây, bạn có thể sử dụng địa chỉ nhận được (ví dụ: hiển thị trong TextView)
            activity.displayAddress(address);
        } else {
            Toast.makeText(activity, "Không tìm thấy địa chỉ", Toast.LENGTH_SHORT).show();
        }
    }
}
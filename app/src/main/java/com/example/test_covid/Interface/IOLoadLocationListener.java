package com.example.test_covid.Interface;

import com.example.test_covid.Model.MyLatLng;

import java.util.List;

public interface IOLoadLocationListener {
    void onLoadLocationSucess(List<MyLatLng> latLngs);
    void onLoadLocationFailed(String message);

}

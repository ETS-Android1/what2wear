package com.example.what2wear.view.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2wear.R;
import com.example.what2wear.adapter.WearableAdapter;
import com.example.what2wear.constant.GenderEnum;
import com.example.what2wear.data.WeatherDao;
import com.example.what2wear.factory.ClothingFactory;
import com.example.what2wear.models.clothing.Top;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class TopFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Top> topList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;
    private WearableAdapter adapter;

    public TopFragment()
    {
        super(R.layout.fragment_top);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DAO
        weatherDao = WeatherDao.getInstance();
        currentWeather = weatherDao.getCurrentWeather();
        currentGender = weatherDao.getGender();

        ClothingFactory factory = ClothingFactory.getInstance(getContext());
        topList = factory.generateTops(currentWeather, currentGender);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_topFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WearableAdapter(getContext(), topList);
        recyclerView.setAdapter(adapter);
    }
}
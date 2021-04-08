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
import com.example.what2wear.models.clothing.Bottom;
import com.example.what2wear.models.clothing.Outwear;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class OutwearFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Outwear> outwearList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;
    private WearableAdapter adapter;

    public OutwearFragment()
    {
        super(R.layout.fragment_outwear);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DAO
        weatherDao = WeatherDao.getInstance();
        currentWeather = weatherDao.getCurrentWeather();
        currentGender = weatherDao.getGender();

        ClothingFactory factory = ClothingFactory.getInstance(getContext());
        outwearList = factory.generateOutwears(currentWeather, currentGender);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_outwearFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WearableAdapter(getContext(), outwearList);
        recyclerView.setAdapter(adapter);
    }
}
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
import com.example.what2wear.models.clothing.Shoes;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class ShoeFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Shoes> shoeList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;
    private WearableAdapter adapter;

    public ShoeFragment()
    {
        super(R.layout.fragment_shoe);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DAO
        weatherDao = WeatherDao.getInstance();
        currentWeather = weatherDao.getCurrentWeather();
        currentGender = weatherDao.getGender();

        ClothingFactory factory = ClothingFactory.getInstance(getContext());
        shoeList = factory.generateShoes(currentWeather, currentGender);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_shoeFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WearableAdapter(getContext(), shoeList);
        recyclerView.setAdapter(adapter);
    }
}
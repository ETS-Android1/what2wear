package com.example.what2wear.view;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.what2wear.R;
import com.example.what2wear.constant.GenderEnum;
import com.example.what2wear.data.WeatherDao;
import com.example.what2wear.factory.ClothingFactory;
import com.example.what2wear.models.clothing.Bottom;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class BottomFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Bottom> bottomList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;

    public BottomFragment()
    {
        super(R.layout.fragment_bottom);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize DAO
        weatherDao = WeatherDao.getInstance();
        currentWeather = weatherDao.getCurrentWeather();
        currentGender = weatherDao.getGender();

        ClothingFactory factory = ClothingFactory.getInstance();
        bottomList = factory.generateBottoms(currentWeather, currentGender);

        TextView bottomText = view.findViewById(R.id.clothingList3);
        String bottomName = bottomList.get(0).getName();
        bottomText.setText(bottomName);

        ImageView bottomImage = view.findViewById(R.id.bottomImageView);
        int resourceId = getActivity().getApplicationContext().getResources().getIdentifier(
                bottomList.get(0).getFileName(),
                "drawable",
                getActivity().getApplicationContext().getPackageName());
        bottomImage.setImageResource(resourceId);
    }
}
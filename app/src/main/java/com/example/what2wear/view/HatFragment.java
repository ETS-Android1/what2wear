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
import com.example.what2wear.models.clothing.Hat;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class HatFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Hat> hatList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;

    public HatFragment()
    {
        super(R.layout.fragment_hat);
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
        hatList = factory.generateHats(currentWeather, currentGender);

        TextView hatText = view.findViewById(R.id.clothingList);
        String hatName = hatList.get(0).getName();
        hatText.setText(hatName);

        ImageView hatImage = view.findViewById(R.id.hatImageView);
        int resourceId = getActivity().getApplicationContext().getResources().getIdentifier(
                hatList.get(0).getFileName(),
                "drawable",
                getActivity().getApplicationContext().getPackageName());
        hatImage.setImageResource(resourceId);
    }
}
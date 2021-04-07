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
import com.example.what2wear.models.clothing.Top;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class TopFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Top> topList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;

    public TopFragment()
    {
        super(R.layout.fragment_top);
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
        topList = factory.generateTops(currentWeather, currentGender);

        TextView hatText = view.findViewById(R.id.clothingList2);
        String topName = topList.get(0).getName();
        hatText.setText(topName);

        ImageView topImage = view.findViewById(R.id.topImageView);
        int resourceId = getActivity().getApplicationContext().getResources().getIdentifier(
                topList.get(0).getFileName(),
                "drawable",
                getActivity().getApplicationContext().getPackageName());
        topImage.setImageResource(resourceId);
    }
}
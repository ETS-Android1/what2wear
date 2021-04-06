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
import com.example.what2wear.models.clothing.Outwear;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class OutwearFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Outwear> outwearList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;

    public OutwearFragment()
    {
        super(R.layout.fragment_outwear);
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
        outwearList = factory.generateOutwears(currentWeather, currentGender);

        TextView outwearText = view.findViewById(R.id.clothingList4);
        String outwearName = outwearList.get(0).getName();
        outwearText.setText(outwearName);

        ImageView outwearImage = view.findViewById(R.id.outwearImageView);
        int resourceId = getActivity().getApplicationContext().getResources().getIdentifier(
                outwearList.get(0).getFileName(),
                "drawable",
                getActivity().getApplicationContext().getPackageName());
        outwearImage.setImageResource(resourceId);
    }
}
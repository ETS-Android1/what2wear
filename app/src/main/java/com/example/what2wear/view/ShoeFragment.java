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
import com.example.what2wear.models.clothing.Shoes;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class ShoeFragment extends Fragment {

    private WeatherDao weatherDao;

    private List<Shoes> shoeList;

    private GenderEnum currentGender;
    private WeatherResponse currentWeather;

    public ShoeFragment()
    {
        super(R.layout.fragment_shoe);
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
        shoeList = factory.generateShoes(currentWeather, currentGender);

        TextView shoeText = view.findViewById(R.id.clothingList5);
        String shoeName = shoeList.get(0).getName();
        shoeText.setText(shoeName);

        ImageView shoeImage = view.findViewById(R.id.shoeImageView);
        int resourceId = getActivity().getApplicationContext().getResources().getIdentifier(
                shoeList.get(0).getFileName(),
                "drawable",
                getActivity().getApplicationContext().getPackageName());
        shoeImage.setImageResource(resourceId);
    }
}
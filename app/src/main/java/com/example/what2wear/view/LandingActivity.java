package com.example.what2wear.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.what2wear.R;
import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoActivityContract;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoPresenterImpl;

public class LandingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

}
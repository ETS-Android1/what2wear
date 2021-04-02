package com.example.what2wear.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.what2wear.R;
import com.example.what2wear.constant.WearableEnum;
import com.example.what2wear.data.WeatherDao;
import com.example.what2wear.factory.ClothingFactory;
import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoActivityContract;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoPresenterImpl;

import java.util.Locale;

public class WeatherInfoActivity extends AppCompatActivity implements WeatherInfoActivityContract.View {
  private static Context mContext;
  private WeatherDao weatherDao;
  private TextView selectedGenderText;
  private TextView currentTemp;
  private TextView weatherDescription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_info);
    mContext = this;


    // Initialize DAO
    weatherDao = WeatherDao.getInstance();

    double latitude = weatherDao.getCurrentPlace().getLatLng().latitude;
    double longitude = weatherDao.getCurrentPlace().getLatLng().latitude;

    //Presenter
    WeatherInfoPresenterImpl presenter = new WeatherInfoPresenterImpl(this);
    presenter.loadWeatherDataByCoordinate(latitude, longitude);


    Button refreshButton = findViewById(R.id.refreshButton_weather);
    refreshButton.setOnClickListener(v -> {
      presenter.loadWeatherDataByCoordinate(latitude, longitude);
    });

    selectedGenderText = findViewById(R.id.selectedGender_weather);
    currentTemp = findViewById(R.id.currentTemp_weather);
    weatherDescription = findViewById(R.id.weatherDescription_weather);

    Button generateButton = findViewById(R.id.generateButton_weather);
    generateButton.setOnClickListener((v) -> {
      Intent intent = new Intent(this, OutfitCategoryActivity.class);
      startActivity(intent);
    });

  }

  public void setTextWithData() {
    selectedGenderText.setText(String.format(Locale.CANADA,"Gender: %s",
            weatherDao.getGender().toString()));
    currentTemp.setText(String.format(Locale.CANADA, "Current Temperature: %f",
            weatherDao.getCurrentWeather().getMain().getTemp()));
    weatherDescription.setText(String.format(Locale.CANADA,"Weather Description: %s",
            weatherDao.getCurrentWeather().getWeather().get(0).getDescription()));
  }

  @Override
  public void showData(WeatherResponse data) {
    weatherDao.setCurrentWeather(data);
    setTextWithData();
  }

  @Override
  public void showError(String message) {

  }

  @Override
  public void showComplete() {

  }

  @Override
  public void showProgress() {

  }

  @Override
  public void hideProgress() {

  }

  public static Context getContext(){
    return mContext;
  }
}
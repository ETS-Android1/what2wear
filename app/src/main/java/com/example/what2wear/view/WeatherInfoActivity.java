package com.example.what2wear.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.what2wear.R;
import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoActivityContract;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoPresenterImpl;

public class WeatherInfoActivity extends AppCompatActivity implements WeatherInfoActivityContract.View {
  private static Context mContext;
  private Button testButton;
  private TextView weatherText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_info);
    mContext = this;

    double latitude = getIntent().getDoubleExtra("Latitude", 0);
    double longitude = getIntent().getDoubleExtra("Longitude", 0);

    //Presenter
    WeatherInfoPresenterImpl presenter = new WeatherInfoPresenterImpl(this);
    weatherText = findViewById(R.id.textView_weather);
    testButton = findViewById(R.id.addressButton_main);
//    testButton.setOnClickListener(v -> {
//      presenter.loadWeatherDataByCoordinate(49.262933, -122.878037);
//    });
    presenter.loadWeatherDataByCoordinate(latitude, longitude);
  }

  @Override
  public void showData(WeatherResponse data) {
    weatherText.setText(data.toString());
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
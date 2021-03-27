package com.example.what2wear.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.what2wear.R;
import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoActivityContract;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoPresenterImpl;

public class WeatherInfoActivity extends AppCompatActivity implements WeatherInfoActivityContract.View {
  private Button testButton;
  private TextView testText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_info);
    WeatherInfoPresenterImpl presenter = new WeatherInfoPresenterImpl(this);
    testText = findViewById(R.id.test_text);
    testButton = findViewById(R.id.testButton_main);
    testButton.setOnClickListener(v -> {
      presenter.loadWeatherDataByCoordinate(49.262933, -122.878037);
    });
  }

  @Override
  public void showData(WeatherResponse data) {
    testText.setText(data.toString());
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
}
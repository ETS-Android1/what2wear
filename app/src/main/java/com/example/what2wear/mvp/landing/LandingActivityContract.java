package com.example.what2wear.mvp.landing;

import com.example.what2wear.models.weather.WeatherResponse;

public interface LandingActivityContract {
  interface View {
    void showData(WeatherResponse data);
    void showError(String message);
    void showComplete();
    void showProgress();
    void hideProgress();
  }

  interface Presenter {
    void loadWeatherDataByCoordinate(double lat, double lon);
  }
}

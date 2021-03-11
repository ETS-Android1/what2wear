package com.example.what2wear.contract;

import android.content.Context;

import com.example.what2wear.models.Weather;

public interface WeatherContract {
  interface Model {

    interface onFinishedListener {
      void onFinished(Weather weather);

      void onFailure(String errorMsg);
    }

    void getWeatherByCoordinate(onFinishedListener onFinishedListener, String lat, String lon);
  }

  interface View {
    void showProgress();

    void hideProgress();

    void showToast(String message);

    void onResponseFailure(Throwable throwable);

  }

  interface Presenter {
    void onDestroy();

    void requestWeatherDataFromServer();

  }
}

package com.example.what2wear.models;

import android.util.Log;

import com.example.what2wear.contract.WeatherContract;
import com.example.what2wear.network.WeatherClient;
import com.example.what2wear.network.WeatherInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherModel implements WeatherContract.Model {
  private final String TAG = "WeatherModel";
  private final static String API_KEY = "e1b082fbc97814e810b880dbdc55c3ae";
  WeatherClient client;
  WeatherInterface service;

  public WeatherModel() {
     client = WeatherClient.getInstance();
     service = WeatherClient.getWeatherService();
  }

  @Override
  public void getWeatherByCoordinate(onFinishedListener onFinishedListener, String lat, String lon) {
    Call<Weather> call = service.getWeatherByCoordinate(lat, lon, API_KEY);

    call.enqueue(new Callback<Weather>() {
      @Override
      public void onResponse(Call<Weather> call, Response<Weather> response) {
        if (!response.isSuccessful()) {
          onFinishedListener.onFailure(
                  RequestFail_Log("WeatherByCoordinate", "onResponse", response)
          );
          onFinishedListener.onFinished(response.body());
          return;
        }
        Weather result = response.body();
      }

      @Override
      public void onFailure(Call<Weather> call, Throwable t) {
        onFinishedListener.onFailure(
                RequestFail_Log("WeatherByCoordinate", "onFailure", t)
        );
      }
    });

  }

  private String RequestFail_Log(String call, String point, Object result) {
    StringBuilder errorMsg = new StringBuilder();

    if (result instanceof Response) {
      Response response = (Response)result;
      errorMsg.append(String.format("%s: %s Failure, Code [%d] message [%s]", point, call, response.code(), response.message()));
    }
    else if (result instanceof Throwable) {
      Throwable t = (Throwable)result;
      errorMsg.append(String.format("%s: %s Failure, message [$s]", point, call, t.getMessage()));
    }
    Log.d(TAG, errorMsg.toString());
    return errorMsg.toString();
  }
}

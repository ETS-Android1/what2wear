package com.example.what2wear.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {
  private static WeatherClient instance = null;
  private static WeatherInterface service;
  private String BASE_URL = "api.openweathermap.org/";

  private WeatherClient() {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    service = retrofit.create(WeatherInterface.class);
  }

  public static WeatherClient getInstance() {
    if (instance == null) {
      instance = new WeatherClient();
    }
    return instance;
  }

  public static WeatherInterface getWeatherService() {
    return service;
  }
}

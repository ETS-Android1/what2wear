package com.example.what2wear.network;

import com.example.what2wear.models.weather.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

  @GET("data/2.5/weather")
  Call<WeatherResponse> getWeatherByCoordinate(@Query("lat") double lat,
                                               @Query("lon") double lon,
                                               @Query("appid") String key);
}

package com.example.what2wear.network;

import com.example.what2wear.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {

  @GET("data/2.5/weather")
  Call<Weather> getWeatherByCoordinate(@Query("lat") String lat,
                                       @Query("lon") String lon,
                                       @Query("appid") String key);
}

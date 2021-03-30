package com.example.what2wear.data;

import com.example.what2wear.constant.GenderEnum;
import com.example.what2wear.models.weather.WeatherResponse;
import com.google.android.libraries.places.api.model.Place;

public class WeatherDao {
  private static WeatherDao weatherDao = null;
  private GenderEnum gender;
  private WeatherResponse currentWeather;
  private Place currentPlace;

  private WeatherDao(){

  }

  public static synchronized WeatherDao getInstance() {
    if (weatherDao == null) {
      weatherDao = new WeatherDao();
    }

    return weatherDao;
  }

  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(GenderEnum gender) {
    this.gender = gender;
  }

  public WeatherResponse getCurrentWeather() {
    return currentWeather;
  }

  public void setCurrentWeather(WeatherResponse currentWeather) {
    this.currentWeather = currentWeather;
  }

  public Place getCurrentPlace() {
    return currentPlace;
  }

  public void setCurrentPlace(Place currentPlace) {
    this.currentPlace = currentPlace;
  }
}

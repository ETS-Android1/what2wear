package com.example.what2wear.mvp.weatherInfo;

import android.content.res.Resources;
import android.util.Log;

import com.example.what2wear.R;
import com.example.what2wear.data.WeatherDao;
import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.network.WeatherAPI;
import com.example.what2wear.network.HttpClient;
import com.example.what2wear.view.WeatherInfoActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInfoPresenterImpl implements WeatherInfoActivityContract.Presenter {
  private static final String BASE_URL = "http://api.openweathermap.org/";
  private WeatherInfoActivityContract.View mView;
  private final WeatherAPI weatherAPI;

  public WeatherInfoPresenterImpl(WeatherInfoActivityContract.View view) {
    this.mView = view;
    weatherAPI = HttpClient.getClient(BASE_URL).create(WeatherAPI.class);
  }

  @Override
  public void loadWeatherDataByCoordinate(double lat, double lon) {
    String key = WeatherInfoActivity.getContext().getString(R.string.weather_key);
    Call<WeatherResponse> call = weatherAPI.getWeatherByCoordinate(lat, lon, "metric", key);

    call.enqueue(new Callback<WeatherResponse>() {
      @Override
      public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
        if (response.isSuccessful()) {
          Log.d("[SUCCESS] retrofit", String.format("Successfully fetched %s", response.body().toString()));
          WeatherResponse weatherResponse = response.body();
          mView.showData(weatherResponse);
        }
      }

      @Override
      public void onFailure(Call<WeatherResponse> call, Throwable t) {
        Log.e("[ERROR] retrofit: ", t.getMessage());
      }
    });
  }
}

package com.example.what2wear.mvp.landing;

import android.util.Log;

import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.network.WeatherAPI;
import com.example.what2wear.network.WeatherClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingPresenterImpl implements LandingActivityContract.Presenter {
  private LandingActivityContract.View mView;
  private WeatherAPI weatherAPI;

  // Retrieves weather api key
  static {
    System.loadLibrary("keys");
  }

  public native String getWeatherKey();

  public LandingPresenterImpl(LandingActivityContract.View view) {
    this.mView = view;
    weatherAPI = WeatherClient.getRetrofit().create(WeatherAPI.class);
  }

  @Override
  public void loadWeatherDataByCoordinate(double lat, double lon) {
    String key = getWeatherKey();
    Call<WeatherResponse> call = weatherAPI.getWeatherByCoordinate(lat, lon, key);

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

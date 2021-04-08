package com.example.what2wear.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.what2wear.R;
import com.example.what2wear.data.WeatherDao;
import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoActivityContract;
import com.example.what2wear.mvp.weatherInfo.WeatherInfoPresenterImpl;
import com.google.android.libraries.places.api.model.AddressComponent;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class WeatherInfoActivity extends AppCompatActivity implements WeatherInfoActivityContract.View {
  private static Context mContext;
  private WeatherDao weatherDao;

  private WeatherInfoPresenterImpl presenter;

  private double latitude;
  private double longitude;

  private ProgressBar progressBar;
  private Button generateButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_info);
    mContext = this;
    // calling the action bar
    ActionBar actionBar = getSupportActionBar();

    // showing the back button in action bar
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setTitle("");

    // Initialize DAO
    weatherDao = WeatherDao.getInstance();

    latitude = weatherDao.getCurrentPlace().getLatLng().latitude;
    longitude = weatherDao.getCurrentPlace().getLatLng().longitude;

    //Presenter
    presenter = new WeatherInfoPresenterImpl(this);
    presenter.loadWeatherDataByCoordinate(latitude, longitude);

    progressBar = findViewById(R.id.progressBar_weather);
    progressBar.setVisibility(View.VISIBLE);

    generateButton = findViewById(R.id.generateButton_weather);
    generateButton.setOnClickListener((v) -> {
      generateButton.setAlpha(0.2f);
      generateButton.setEnabled(false);
      progressBar.setVisibility(View.VISIBLE);
      Intent intent = new Intent(this, OutfitCategoryActivity.class);
      startActivity(intent);
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.refresh_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.refresh_button) {
      Toast.makeText(getContext(), "Refreshing...", Toast.LENGTH_SHORT).show();
      presenter.loadWeatherDataByCoordinate(latitude, longitude);
    } else if (id == android.R.id.home) {
      this.finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void setData() {
    WeatherResponse weather = weatherDao.getCurrentWeather();

    TextView currentDayText = findViewById(R.id.currentDay_weather);
    TextView cityText = findViewById(R.id.city_weather);
    TextView countryText = findViewById(R.id.country_weather);
    TextView currentWeatherText = findViewById(R.id.currentWeather_weather);
    TextView currentTempText = findViewById(R.id.currentTemp_weather);
    TextView minTempText = findViewById(R.id.minTemp_weather);
    TextView maxTempText = findViewById(R.id.maxTemp_weather);
    TextView feelsLikeText = findViewById(R.id.feelsLike_weather);
    TextView windText = findViewById(R.id.wind_weather);
    TextView humidityText = findViewById(R.id.humidity_weather);


    List<AddressComponent> testList = weatherDao.getCurrentPlace().getAddressComponents().asList();

    currentDayText.setText(LocalDate.now().getDayOfWeek().name());
    cityText.setText(testList.get(0).getName().toUpperCase());
    countryText.setText(testList.get(testList.size() - 1).getName().toUpperCase());
    currentWeatherText.setText(weather.getWeather().get(0).getMain().toUpperCase());
    currentTempText.setText(String.format(Locale.getDefault(), "%d℃",
            weather.getMain().getTemp().intValue()));
    minTempText.setText(String.format(Locale.getDefault(), "%d°",
            weather.getMain().getTempMin().intValue()));
    maxTempText.setText(String.format(Locale.getDefault(), "%d°",
            weather.getMain().getTempMax().intValue()));
    feelsLikeText.setText(String.format(Locale.getDefault(), "%d°",
            weather.getMain().getFeelsLike().intValue()));
    windText.setText(String.format(Locale.getDefault(), "%d km/h",
            weather.getWind().getSpeed().intValue()));
    humidityText.setText(String.format(Locale.getDefault(), "%d %%",
            weather.getMain().getHumidity()));

    ImageView weatherImage = findViewById(R.id.weatherImage_weather);
    String iconUrl = String.format("http://openweathermap.org/img/wn/%s@2x.png",
            weather.getWeather().get(0).getIcon());
    Glide.with(this).load(iconUrl).into(weatherImage);
  }

  @Override
  public void showData(WeatherResponse data) {
    weatherDao.setCurrentWeather(data);
    if (data.getMain().getFeelsLike() == null) {
      data.getMain().setFeelsLike(data.getMain().getTemp());
    }
    setData();
    progressBar.setVisibility(View.INVISIBLE);
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

  public static Context getContext(){
    return mContext;
  }


  @Override
  protected void onStop() {
    generateButton.setEnabled(true);
    generateButton.setAlpha(1);
    progressBar.setVisibility(View.INVISIBLE);
    super.onStop();
  }
}
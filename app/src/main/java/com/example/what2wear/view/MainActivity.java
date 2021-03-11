package com.example.what2wear.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.what2wear.R;
import com.example.what2wear.contract.WeatherContract;

public class MainActivity extends AppCompatActivity implements WeatherContract.View {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


  }

  @Override
  public void showProgress() {

  }

  @Override
  public void hideProgress() {

  }

  @Override
  public void showToast(String message) {

  }

  @Override
  public void onResponseFailure(Throwable throwable) {

  }
}
package com.example.what2wear.presenters;

import com.example.what2wear.contract.WeatherContract;
import com.example.what2wear.models.Weather;
import com.example.what2wear.models.WeatherModel;

public class MainPresenter implements WeatherContract.Presenter, WeatherContract.Model.onFinishedListener {
  private  WeatherContract.View view;
  private  WeatherContract.Model model;

  public MainPresenter(WeatherContract.View view) {
    this.view = view;
    this.model = new WeatherModel();
  }

  @Override
  public void onDestroy() {
    view = null;
  }

  @Override
  public void requestWeatherDataFromServer() {
    if (view != null) {
      view.showProgress();
    }
//    model.getWeatherByCoordinate(this);
  }

  @Override
  public void onFinished(Weather weather) {
    if (view != null) {
//      view.hideProgress();
//      adapterModel.setData(users);	// Adatper에 Data 추가
//      adapterView.notifyAdapter();	// RecyclerView 갱신
    }
  }

  @Override
  public void onFailure(String errorMsg) {

  }
}

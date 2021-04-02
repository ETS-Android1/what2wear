package com.example.what2wear.factory;

import com.example.what2wear.constant.GenderEnum;
import com.example.what2wear.models.clothing.Bottom;
import com.example.what2wear.models.clothing.Hat;
import com.example.what2wear.models.clothing.Outwear;
import com.example.what2wear.models.clothing.Shoes;
import com.example.what2wear.models.clothing.Top;
import com.example.what2wear.models.clothing.Wearable;
import com.example.what2wear.models.weather.WeatherResponse;
import com.example.what2wear.view.WeatherInfoActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClothingFactory {
  private static ClothingFactory clothingFactory = null;

  private final List<Hat> hatList = new ArrayList<>();
  private final List<Top> topList = new ArrayList<>();
  private final List<Bottom> bottomList = new ArrayList<>();
  private final List<Shoes> shoesList = new ArrayList<>();
  private final List<Outwear> outwearList = new ArrayList<>();

  public static synchronized ClothingFactory getInstance() {
    if (clothingFactory == null) {
      clothingFactory = new ClothingFactory();
    }

    return clothingFactory;
  }
  private ClothingFactory() {
    stockInventory();
  }

  private void stockInventory() {
    try {
      JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
      JSONArray hatArray = jsonObject.getJSONArray("HAT");
      JSONArray topArray = jsonObject.getJSONArray("TOP");
      JSONArray bottomArray = jsonObject.getJSONArray("BOTTOM");
      JSONArray outwearArray = jsonObject.getJSONArray("OUTWEAR");
      JSONArray shoesArray = jsonObject.getJSONArray("SHOES");

      Gson gson = new Gson();
      for (int i = 0; i < hatArray.length(); i++) {
        JSONObject item = hatArray.getJSONObject(i);
        Hat hat = gson.fromJson(item.toString(), Hat.class);
        hatList.add(hat);
      }
      for (int i = 0; i < topArray.length(); i++) {
        JSONObject item = topArray.getJSONObject(i);
        Top top = gson.fromJson(item.toString(), Top.class);
        topList.add(top);
      }

      for (int i = 0; i < bottomArray.length(); i++) {
        JSONObject item = bottomArray.getJSONObject(i);
        Bottom bottom = gson.fromJson(item.toString(), Bottom.class);
        bottomList.add(bottom);
      }

      for (int i = 0; i < outwearArray.length(); i++) {
        JSONObject item = outwearArray.getJSONObject(i);
        Outwear outwear = gson.fromJson(item.toString(), Outwear.class);
        outwearList.add(outwear);
      }

      for (int i = 0; i < shoesArray.length(); i++) {
        JSONObject item = shoesArray.getJSONObject(i);
        Shoes shoes = gson.fromJson(item.toString(), Shoes.class);
        shoesList.add(shoes);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }


  private String loadJSONFromAsset() {
    String json;
    try {
      InputStream is = WeatherInfoActivity.getContext().getAssets().open("jsons/WearableData.json");
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
    return json;
  }

  public List<Top> generateTops(WeatherResponse currentWeather, GenderEnum gender){
    // filter out appropriate
    return topList;
  }

  public List<Bottom> generateBottoms(WeatherResponse currentWeather, GenderEnum gender){
    return bottomList;
  }

  public List<Outwear> generateOutwears(WeatherResponse currentWeather, GenderEnum gender){
    return outwearList;
  }

  public List<Shoes> generateShoes(WeatherResponse currentWeather, GenderEnum gender){
    return shoesList;
  }

  public List<Hat> generateHats(WeatherResponse currentWeather, GenderEnum gender){
    return hatList;
  }
}

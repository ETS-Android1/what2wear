package com.example.what2wear.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.what2wear.R;
import com.example.what2wear.constant.GenderEnum;
import com.example.what2wear.data.WeatherDao;
import com.example.what2wear.factory.ClothingFactory;
import com.example.what2wear.models.clothing.Bottom;
import com.example.what2wear.models.clothing.Hat;
import com.example.what2wear.models.clothing.Outwear;
import com.example.what2wear.models.clothing.Shoes;
import com.example.what2wear.models.clothing.Top;
import com.example.what2wear.models.weather.WeatherResponse;

import java.util.List;

public class OutfitCategoryActivity extends AppCompatActivity {
  private WeatherDao weatherDao;

  private List<Hat> hatList;
  private List<Top> topList;
  private List<Bottom> bottomList;
  private List<Shoes> shoesList;
  private List<Outwear> outwearListList;

  private GenderEnum currentGender;
  private WeatherResponse currentWeather;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_outfit_category);

    // Initialize DAO
    weatherDao = WeatherDao.getInstance();
    currentWeather = weatherDao.getCurrentWeather();
    currentGender = weatherDao.getGender();

    ClothingFactory factory = ClothingFactory.getInstance();
    hatList = factory.generateHats(currentWeather, currentGender);
    topList = factory.generateTops(currentWeather, currentGender);
    bottomList = factory.generateBottoms(currentWeather, currentGender);
    shoesList = factory.generateShoes(currentWeather, currentGender);
    outwearListList = factory.generateOutwears(currentWeather, currentGender);

    TextView hatText = findViewById(R.id.clothingList);
    String hatName = hatList.get(0).getName();
    hatText.setText(hatName);

    ImageView hatImage = findViewById(R.id.hatImageView);
    int resourceId = getApplicationContext().getResources().getIdentifier(
            hatList.get(0).getFileName(),
            "drawable",
            getApplicationContext().getPackageName());
    hatImage.setImageResource(resourceId);
  }
}
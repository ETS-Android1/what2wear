package com.example.what2wear.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.what2wear.R;
import com.example.what2wear.constant.GenderEnum;
import com.example.what2wear.data.WeatherDao;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class LandingActivity extends AppCompatActivity {
  private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
  private static final String TAG = "Google Places";

  // Set the fields to specify which types of place data to
  // return after the user has made a selection.
  private List<Place.Field> fields = Arrays.asList(
          Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);
  private Button addressButton;
  private Button nextButton;
  private TextView currentLocation;

  private WeatherDao weatherDao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setTheme(R.style.Theme_What2Wear);
    getSupportActionBar().hide();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_landing);

    // Initialize DAO
    weatherDao = WeatherDao.getInstance();

    // Initialize the places SDK
    Places.initialize(getApplicationContext(), getString(R.string.google_places_key));

    //Initialize Widgets
    addressButton = findViewById(R.id.addressButton_main);
    nextButton = findViewById(R.id.nextButton_landing);
    currentLocation = findViewById(R.id.currentLocation_landing);

    //Button to address setup
    addressButton.setOnClickListener(v -> {
      Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
              .build(this);
      startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    });

    //Next button handling
    validateSaveButton();
    nextButton.setOnClickListener(v -> {
      Intent intent = new Intent(this, WeatherInfoActivity.class);
      startActivity(intent);
    });
  }

  public void validateSaveButton() {
    nextButton.setEnabled(weatherDao.getGender() != null && weatherDao.getCurrentPlace() != null);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Place place = Autocomplete.getPlaceFromIntent(data);
        weatherDao.setCurrentPlace(place);
        Log.i(TAG, "Place: " + place.getName() + ", " + place.getId()
                + "Latitude: " + place.getLatLng().latitude
                + "Longitude: " + place.getLatLng().longitude);
        currentLocation.setText(place.getAddress());
        validateSaveButton();
      } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
        Status status = Autocomplete.getStatusFromIntent(data);
        Log.i(TAG, status.getStatusMessage());
        Toast.makeText(this, status.getStatusMessage(), Toast.LENGTH_LONG).show();
      } else if (resultCode == RESULT_CANCELED) {
        // The user canceled the operation.
      }
      return;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  public void onRadioButtonClicked(View view) {
    boolean checked = ((RadioButton) view).isChecked();

    // Check which radio button was clicked
    switch(view.getId()) {
      case R.id.maleButton_landing:
        if (checked)
          weatherDao.setGender(GenderEnum.MALE);
          break;
      case R.id.femaleButton_landing:
        if (checked)
          weatherDao.setGender(GenderEnum.FEMALE);
          break;
    }
    validateSaveButton();
  }
}
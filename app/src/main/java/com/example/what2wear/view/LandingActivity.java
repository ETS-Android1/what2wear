package com.example.what2wear.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.what2wear.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class LandingActivity extends AppCompatActivity {
  private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
  private static final String TAG = "Google Places";

  // Set the fields to specify which types of place data to
  // return after the user has made a selection.
  List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
  PlacesClient placesClient;
  LatLng selectedCoordinate;
  TextView textView;
  Button addressButton;
  Button nextButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setTheme(R.style.Theme_What2Wear);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_landing);

    // Initialize the places SDK
    Places.initialize(getApplicationContext(), getString(R.string.google_places_key));
    // Create a new PlacesClient instance
    placesClient = Places.createClient(this);

    //Initialize Widgets
    textView = findViewById(R.id.test_text);
    addressButton = findViewById(R.id.addressButton_main);
    nextButton = findViewById(R.id.nextButton_landing);

    //Button to address setup
    addressButton.setOnClickListener(v -> {
      Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
              .build(this);
      startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    });

    //Next button handling
    if (selectedCoordinate == null) {
      nextButton.setEnabled(false);
    }
    nextButton.setOnClickListener(v -> {
      Intent intent = new Intent(this, WeatherInfoActivity.class);
      intent.putExtra("Latitude", selectedCoordinate.latitude);
      intent.putExtra("Longitude", selectedCoordinate.longitude);
      startActivity(intent);
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Place place = Autocomplete.getPlaceFromIntent(data);
        selectedCoordinate = place.getLatLng();
        Log.i(TAG, "Place: " + place.getName() + ", " + place.getId()
                + "Latitude: " + selectedCoordinate.latitude
                + "Longitude: " + selectedCoordinate.longitude);
        nextButton.setEnabled(true);
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
}
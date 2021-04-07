package com.example.what2wear.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class OutfitCategoryActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_outfit_category);


    SectionsPageAdapter pagerAdapter = new SectionsPageAdapter(getSupportFragmentManager(),
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    ViewPager pager = (ViewPager) findViewById(R.id.pager);
    pager.setAdapter(pagerAdapter);

    TabLayout tabLayout = findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(pager);

    // calling the action bar
    ActionBar actionBar = getSupportActionBar();

    // showing the back button in action bar
    actionBar.setDisplayHomeAsUpEnabled(true);

  }

  // this event will enable the back
  // function to the button on press
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        this.finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public class SectionsPageAdapter extends FragmentPagerAdapter {

    public SectionsPageAdapter(FragmentManager fm, int behaviour) { super(fm, behaviour); }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return new HatFragment();
        case 1:
          return new TopFragment();
        case 2:
          return new BottomFragment();
        case 3:
          return new OutwearFragment();
        case 4:
          return new ShoeFragment();
      }
      return null;
    }

    @Override
    public int getCount() {
      return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return getString(R.string.tab_hats);
        case 1:
          return getString(R.string.tab_tops);
        case 2:
          return getString(R.string.tab_bottoms);
        case 3:
          return getString(R.string.tab_outwear);
        case 4:
          return getString(R.string.tab_shoes);
      }
      return null;
    }
  }
}
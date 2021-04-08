package com.example.what2wear.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.what2wear.R;
import com.example.what2wear.view.fragments.BottomFragment;
import com.example.what2wear.view.fragments.HatFragment;
import com.example.what2wear.view.fragments.OutwearFragment;
import com.example.what2wear.view.fragments.ShoeFragment;
import com.example.what2wear.view.fragments.TopFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OutfitCategoryActivity extends AppCompatActivity {

  private ViewPager2 viewPager;
  private ScreenSlidePagerAdapter pageAdapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_outfit_category);

    viewPager = (ViewPager2) findViewById(R.id.pager);
    pageAdapter = new ScreenSlidePagerAdapter(this);
    viewPager.setAdapter(pageAdapter);
    viewPager.setOffscreenPageLimit(5);

    // calling the action bar
    ActionBar actionBar = getSupportActionBar();
    actionBar.setTitle("Outfit Recommendation");

    // showing the back button in action bar
    actionBar.setDisplayHomeAsUpEnabled(true);


    TabLayout tabLayout = findViewById(R.id.tab_layout);
    new TabLayoutMediator(tabLayout, viewPager,
            (tab, position) -> tab.setText(getPageTitle(position))
    ).attach();

  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
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

  /**
   * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
   * sequence.
   */
  private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    public ScreenSlidePagerAdapter(FragmentActivity fa) {
      super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
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
    public int getItemCount() {
      return 5;
    }
  }

}
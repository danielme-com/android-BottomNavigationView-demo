package com.danielme.android.bottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.danielme.android.bottomnavigation.fragments.PageFragment;
import com.danielme.android.bottomnavigation.fragments.RecyclerViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  private static final String SELECTION = "SELECTION";

  private BottomNavigationView bottomNavigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
    setupBottomMenu(savedInstanceState);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(SELECTION, bottomNavigationView.getSelectedItemId());
  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  private void setupBottomMenu(Bundle savedInstanceState) {
    bottomNavigationView = findViewById(R.id.bottom_navigation);
    bottomNavigationView.setOnItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.page_home:
          showFragment(PageFragment.newInstance(R.drawable.baseline_home_black_48));
          break;
        case R.id.page_list:
          showFragment(new RecyclerViewFragment());
          break;
        case R.id.page_fav:
          showFragment(PageFragment.newInstance(R.drawable.baseline_favorite_black_48));
          break;
        case R.id.page_search:
          showFragment(PageFragment.newInstance(R.drawable.baseline_search_black_48));
          break;
        case R.id.page_settings:
          showFragment(PageFragment.newInstance(R.drawable.baseline_app_settings_alt_black_48));
          break;
        default:
          throw new IllegalArgumentException("item not implemented : " + item.getItemId());
      }
      return true;
    });

    //setear aquí para que el listener muestre el fragment inicial al cargarse la pantalla
    if (savedInstanceState == null) {
      bottomNavigationView.setSelectedItemId(R.id.page_home);
    } else {
      bottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECTION));
    }

    bottomNavigationView.getOrCreateBadge(R.id.page_fav).setNumber(1000);
    bottomNavigationView.getOrCreateBadge(R.id.page_settings).setVisible(true);
  }

  private void showFragment(Fragment frg) {
    getSupportFragmentManager()
            .beginTransaction()
            .setCustomAnimations(R.anim.bottom_nav_enter, R.anim.bottom_nav_exit)
            .replace(R.id.container, frg)
            .commit();
  }


}

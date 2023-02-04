package com.danielme.android.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
    //setupNavigation();
  }

  /*private void setupNavigation() {
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    NavController navController = navHostFragment.getNavController();
    NavigationUI.setupWithNavController(bottomNavigationView,
            navController);

    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.page_home,
            R.id.page_fav, R.id.page_list, R.id.page_settings, R.id.page_search)
            .build();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
  }*/

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
    bottomNavigationView.setOnItemSelectedListener(this::onItemSelectedListener);

    //setear aquí para que el listener muestre el fragment inicial al cargarse la pantalla
    if (savedInstanceState == null) {
      bottomNavigationView.setSelectedItemId(R.id.page_home);
    } else {
      bottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECTION));
    }

    bottomNavigationView.getOrCreateBadge(R.id.page_fav).setNumber(1000);
    bottomNavigationView.getOrCreateBadge(R.id.page_settings).setVisible(true);
  }

  private boolean onItemSelectedListener(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.page_home:
        showPageFragment(R.drawable.baseline_home_black_48, R.string.bottom_nav_home);
        return true;
      case R.id.page_list:
        showFragment(new RecyclerViewFragment(), R.string.bottom_nav_list);
        return true;
      case R.id.page_fav:
        showPageFragment(R.drawable.baseline_favorite_black_48, R.string.bottom_nav_fav);
        return true;
      case R.id.page_search:
        showPageFragment(R.drawable.baseline_search_black_48, R.string.bottom_nav_search);
        return true;
      case R.id.page_settings:
        showPageFragment(R.drawable.baseline_app_settings_alt_black_48, R.string.bottom_nav_settings);
        return true;
      default:
        throw new IllegalArgumentException("item not implemented : " + item.getItemId());
    }
  }

  private void showPageFragment(@DrawableRes int iconId, @StringRes int title) {
    showFragment(PageFragment.newInstance(iconId), title);
  }

  private void showFragment(Fragment frg, @StringRes int title) {
    getSupportFragmentManager()
            .beginTransaction()
            .setCustomAnimations(R.anim.bottom_nav_enter, R.anim.bottom_nav_exit)
            .replace(R.id.container, frg)
            .commit();
    setTitle(title);
  }

}

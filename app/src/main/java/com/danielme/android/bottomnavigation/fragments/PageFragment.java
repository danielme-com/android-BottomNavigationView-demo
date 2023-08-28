package com.danielme.android.bottomnavigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.danielme.android.bottomnavigation.R;

public class PageFragment extends Fragment {

  private static final String ARG_ICON = "ARG_ICON";

  public static PageFragment newInstance(@DrawableRes int iconId) {
    PageFragment frg = new PageFragment();

    Bundle args = new Bundle();
    args.putInt(ARG_ICON, iconId);
    frg.setArguments(args);

    return frg;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
          Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.fragment_page, container, false);
    int icon = getArguments().getInt(ARG_ICON);
    layout.findViewById(R.id.imageView).setBackgroundResource(icon);
    return layout;
  }

}

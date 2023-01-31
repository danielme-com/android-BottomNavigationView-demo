package com.danielme.android.bottomnavigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danielme.android.bottomnavigation.R;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecyclerViewFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
  Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.fragment_recycler_view, container, false);

    RecyclerView recyclerView = layout.findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    SimpleTextRecyclerViewAdapter adapter = new SimpleTextRecyclerViewAdapter(getContext(), buildSampleData());
    recyclerView.setAdapter(adapter);

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
            DividerItemDecoration.VERTICAL);
    recyclerView.addItemDecoration(dividerItemDecoration);

    return layout;
  }

  private List<String> buildSampleData() {
    return IntStream.range(0, 25).boxed().map(i -> "item " + i).collect(Collectors.toList());
  }

}

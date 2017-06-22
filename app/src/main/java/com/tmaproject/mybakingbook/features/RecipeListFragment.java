package com.tmaproject.mybakingbook.features;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tmaproject.mybakingbook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment implements RecipeListContract.View {

  @BindView(R.id.recycler_home) RecyclerView recycler;
  @BindView(R.id.loading_layout_home) ViewGroup layoutLoading;
  @BindView(R.id.error_layout_home) ViewGroup layoutError;
  @BindView(R.id.error_text) TextView textError;

  private Unbinder unbinder;

  public RecipeListFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

    unbinder = ButterKnife.bind(this, view);

    return view;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  @Override public void showLoading() {
    layoutLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    layoutLoading.setVisibility(View.GONE);
  }

  @Override public void showError(String error) {
    layoutError.setVisibility(View.VISIBLE);
    textError.setText(error);
  }

  @Override public void hideError() {
    layoutError.setVisibility(View.GONE);
  }

  @Override public void showItems() {

  }
}

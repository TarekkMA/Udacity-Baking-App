package com.tmaproject.mybakingbook.features;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tmaproject.mybakingbook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment implements RecipeListContract.View{



  private Unbinder unbinder;

  public RecipeListFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

    unbinder = ButterKnife.bind(this,view);

    return view;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void showError(String error) {

  }

  @Override public void hideError() {

  }

  @Override public void showItems() {

  }
}

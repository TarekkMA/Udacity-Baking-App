package com.tmaproject.mybakingbook.presenter.RecipeDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.squareup.picasso.Picasso;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.Callbacks;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.item_views.IngredientItemView;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.item_views.StepItemView;
import java.util.List;
import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailsFragment extends Fragment implements RecipeDetailsContract.View {

  private static final String KEY_RECIPE_ID = "RECIPE_ID";

  @BindView(R.id.background_image) ImageView recipeImageView;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout toolbarLayout;

  @BindView(R.id.ingredient_layout_list) LinearLayout ingredientsListLayout;
  @BindView(R.id.steps_layout_list) LinearLayout stepsListLayout;

  private RecipeDetailsContract.Presenter presenter;
  private int recipeId;

  private Unbinder unbinder;

  private Callbacks.StepClicked callback;

  public void setCallback(Callbacks.StepClicked callback) {
    this.callback = callback;
  }

  public static RecipeDetailsFragment newInstance(int recipeId) {
    Bundle args = new Bundle();
    RecipeDetailsFragment fragment = new RecipeDetailsFragment();
    args.putInt(KEY_RECIPE_ID, recipeId);
    fragment.setArguments(args);
    return fragment;
  }



  public RecipeDetailsFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    recipeId = getArguments().getInt(KEY_RECIPE_ID);
    presenter = new RecipeDetailsPresenter(this, recipeId);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.subscribe();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.unsubscribe();
  }

  @Override public void showRecipeDetails(Recipe recipe) {
    Picasso.with(getContext()).load(recipe.getImage()).into(recipeImageView);
    toolbarLayout.setTitle(recipe.getName());
  }

  @Override public void showIngredients(List<Ingredient> ingredientList) {
    ingredientsListLayout.removeAllViews();
    for (Ingredient ingredient : ingredientList) {
      ingredientsListLayout.addView(new IngredientItemView(getContext()).bind(ingredient));
    }
  }

  @Override public void showSteps(List<Step> stepList) {
    stepsListLayout.removeAllViews();
    for (Step step : stepList) {
      stepsListLayout.addView(new StepItemView(getContext(), callback).bind(step));
    }
  }

  @Override public void showMessage(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }
}

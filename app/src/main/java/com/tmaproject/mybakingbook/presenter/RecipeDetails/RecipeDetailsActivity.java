package com.tmaproject.mybakingbook.presenter.RecipeDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.Utils.ResponsiveUi;
import com.tmaproject.mybakingbook.presenter.Callbacks;
import com.tmaproject.mybakingbook.presenter.Steps.StepsActivity;
import com.tmaproject.mybakingbook.presenter.Steps.StepsFragment;

public class RecipeDetailsActivity extends AppCompatActivity {

  private static final String KEY_RECIPE_ID = "RECIPE_ID";

  private static final String TAG_STEPS_FRAGMENT = "TAG_STEPS_FRAGMENT";
  private static final String TAG_DETAILS_FRAGMENT = "TAG_DETAILS_FRAGMENT";

  public static void startThisActivity(Context context, int recipeId) {
    context.startActivity(
        new Intent(context, RecipeDetailsActivity.class).putExtra(KEY_RECIPE_ID, recipeId));
  }

  private int recipeId;
  RecipeDetailsFragment recipeDetailsDFragment = null;
  StepsFragment stepsFragment = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_details);

    recipeId = getIntent().getIntExtra(KEY_RECIPE_ID, -1);

    if (savedInstanceState == null) {
      recipeDetailsDFragment = RecipeDetailsFragment.newInstance(recipeId);

      if (ResponsiveUi.isTablet()) {
        stepsFragment = StepsFragment.newInstance(recipeId, 0/* Start Default */);
      }

      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragmentFrame, recipeDetailsDFragment, TAG_DETAILS_FRAGMENT)
          .commit();
      if (stepsFragment != null) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragmentFrame2, stepsFragment, TAG_STEPS_FRAGMENT)
            .commit();
      }
    }

    setFragmentCallbacks();
  }

  private void setFragmentCallbacks() {
    //setup listeners
    if (recipeDetailsDFragment == null) {
      recipeDetailsDFragment =
          (RecipeDetailsFragment) getSupportFragmentManager().findFragmentByTag(
              TAG_DETAILS_FRAGMENT);
    }
    if (stepsFragment == null) {
      stepsFragment =
          (StepsFragment) getSupportFragmentManager().findFragmentByTag(TAG_STEPS_FRAGMENT);
    }

    if (ResponsiveUi.isTablet()) {
      recipeDetailsDFragment.setCallback(stepsFragment);
    } else {
      recipeDetailsDFragment.setCallback(
          step -> StepsActivity.startThisActivity(this, recipeId, step.getIndex()));
    }
  }
}

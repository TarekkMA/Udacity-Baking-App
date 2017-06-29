package com.tmaproject.mybakingbook.presenter.RecipeDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.Utils.ResponsiveUi;
import com.tmaproject.mybakingbook.presenter.Steps.StepsActivity;
import com.tmaproject.mybakingbook.presenter.Steps.StepsFragment;

public class RecipeDetailsActivity extends AppCompatActivity {

  private static final String KEY_RECIPE_ID = "RECIPE_ID";

  public static void startThisActivity(Context context, int recipeId) {
    context.startActivity(
        new Intent(context, RecipeDetailsActivity.class).putExtra(KEY_RECIPE_ID, recipeId));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_details);

    int recipeId = getIntent().getIntExtra(KEY_RECIPE_ID, -1);
    RecipeDetailsFragment recipeDetailsDFragment = RecipeDetailsFragment.newInstance(recipeId);
    StepsFragment stepsFragment = null;

    if (ResponsiveUi.isTablet()) {
      stepsFragment = StepsFragment.newInstance(recipeId, 0/* Start Default */);
      recipeDetailsDFragment.setCallback(stepsFragment);
    } else {
      recipeDetailsDFragment.setCallback(step -> StepsActivity.startThisActivity(this,recipeId,step.getIndex()));
    }

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentFrame, recipeDetailsDFragment)
        .commit();
    if (stepsFragment != null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragmentFrame2, stepsFragment)
          .commit();
    }
  }
}

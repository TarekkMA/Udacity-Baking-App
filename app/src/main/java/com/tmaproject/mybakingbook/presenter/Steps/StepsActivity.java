package com.tmaproject.mybakingbook.presenter.Steps;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsActivity;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsFragment;

public class StepsActivity extends AppCompatActivity {
  private static final String KEY_RECIPE_ID = "RECIPE_ID";
  private static final String KEY_STEP_ID = "STEP_ID";

  public static void startThisActivity(Context context, int recipeId,int stepId) {
    context.startActivity(
        new Intent(context, StepsActivity.class).putExtra(KEY_RECIPE_ID, recipeId).putExtra(KEY_STEP_ID,stepId));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_steps);

    if(savedInstanceState == null) {
      int recipeId = getIntent().getIntExtra(KEY_RECIPE_ID, -1);
      int stepId = getIntent().getIntExtra(KEY_STEP_ID, -1);
      Fragment fragment = StepsFragment.newInstance(recipeId, stepId);

      getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, fragment).commit();
    }
  }
}

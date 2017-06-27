package com.tmaproject.mybakingbook.presenter.RecipeDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.tmaproject.mybakingbook.R;

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
    Fragment fragment = RecipeDetailsFragment.newInstance(recipeId);

    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, fragment).commit();
  }
}

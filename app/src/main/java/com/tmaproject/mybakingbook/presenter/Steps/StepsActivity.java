package com.tmaproject.mybakingbook.presenter.Steps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.R;

public class StepsActivity extends AppCompatActivity {
  public static final String KEY_RECIPE_ID = "RECIPE_ID";
  public static final String KEY_STEP_ID = "STEP_ID";

  private static final String TAG_STEPS_FRAGMENT = "TAG_STEPS_FRAGMENT";

  public static void startThisActivity(Context context, int recipeId, int stepId) {
    context.startActivity(new Intent(context, StepsActivity.class).putExtra(KEY_RECIPE_ID, recipeId)
        .putExtra(KEY_STEP_ID, stepId));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_steps);

    StepsFragment stepsFragment = null;
    int recipeId = getIntent().getIntExtra(KEY_RECIPE_ID, -1);

    if (savedInstanceState == null) {
      int stepId = getIntent().getIntExtra(KEY_STEP_ID, -1);
      stepsFragment = StepsFragment.newInstance(recipeId, stepId);

      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fragmentFrame, stepsFragment, TAG_STEPS_FRAGMENT)
          .commit();
    }

    if (stepsFragment == null) {
      stepsFragment =
          (StepsFragment) getSupportFragmentManager().findFragmentByTag(TAG_STEPS_FRAGMENT);
    }

    StepsContract.Presenter stepsPresenter = App.get().presenterProvider.provideSteps();
    stepsPresenter.setRecipeId(recipeId);
    stepsFragment.setPresenter(stepsPresenter);

  }
}

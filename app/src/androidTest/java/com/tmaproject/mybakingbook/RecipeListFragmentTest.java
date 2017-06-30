package com.tmaproject.mybakingbook;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatImageButton;
import com.tmaproject.mybakingbook.presenter.RecipeList.MainActivity;
import com.tmaproject.mybakingbook.presenter.RecipeList.RecipeListContract;
import com.tmaproject.mybakingbook.presenter.RecipeList.RecipeListFragment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

/**
 * Created by tarekkma on 6/30/17.
 */

@RunWith(AndroidJUnit4.class) public class RecipeListFragmentTest {
  @Rule public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule<MainActivity>(MainActivity.class);

  RecipeListContract.Presenter mockPresenter = new RecipeListContract.Presenter() {
    @Override public void getRecipeList() {

    }

    @Override public void syncData() {

    }

    @Override public void subscribe() {

    }

    @Override public void unsubscribe() {

    }
  };


}

package com.tmaproject.mybakingbook;

import android.content.Intent;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.tmaproject.mybakingbook.data.RecipeFakeData;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.matchers.RecyclerViewMatcher;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsActivity;
import com.tmaproject.mybakingbook.presenter.RecipeList.MainActivity;
import com.tmaproject.mybakingbook.presenter.RecipeList.RecipeListContract;
import com.tmaproject.mybakingbook.presenter.RecipeList.RecipeListPresenter;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by TarekLMA on 6/30/17.
 * tarekkma@gmail.com
 */

@RunWith(AndroidJUnit4.class) public class RecipeListTest {
  @Rule public IntentsTestRule<MainActivity> activityTestRule =
      new IntentsTestRule<>(MainActivity.class, true, false);

  private RecipeListContract.Presenter presenter = new RecipeListPresenter();

  private RecipeListContract.View view;

  private List<Recipe> RECIPE_LIST;

  public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
    return new RecyclerViewMatcher(recyclerViewId);
  }

  @Before public void setUp() throws Throwable {
    PresenterProvider spyPresenterProvider = Mockito.spy(PresenterProvider.class);
    Mockito.when(spyPresenterProvider.provideRecipeList()).thenReturn(presenter);

    App.get().setPresenterProvider(spyPresenterProvider);
    activityTestRule.launchActivity(new Intent());

    RECIPE_LIST = RecipeFakeData.getRecipeList();
    view = presenter.getView();

    activityTestRule.runOnUiThread(() -> view.showItems(RECIPE_LIST));
  }

  @Test public void testItemDisplayed() {

    int TESTING_POS = 2;
    Recipe TESTING_RECIPE = RECIPE_LIST.get(TESTING_POS);

    onView(withRecyclerView(R.id.recycler_home).atPosition(TESTING_POS)).check(matches(
        hasDescendant(allOf(withId(R.id.text_recipe_item), withText(TESTING_RECIPE.getName())))));
  }

  @Test public void testItemClickedIntent() {
    int TESTING_POS = 1;
    int TESTING_ID = RECIPE_LIST.get(TESTING_POS).getId();

    onView(withRecyclerView(R.id.recycler_home).atPosition(TESTING_POS)).perform(click());

    intended(IntentMatchers.hasExtra(is(RecipeDetailsActivity.KEY_RECIPE_ID), is(TESTING_ID)));
  }
}

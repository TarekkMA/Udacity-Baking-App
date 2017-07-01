package com.tmaproject.mybakingbook;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.tmaproject.mybakingbook.data.RecipeFakeData;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.Steps.StepsActivity;
import com.tmaproject.mybakingbook.presenter.Steps.StepsContract;
import com.tmaproject.mybakingbook.presenter.Steps.StepsPresenter;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by TarekLMA on 7/2/17.
 * tarekkma@gmail.com
 */
@RunWith(AndroidJUnit4.class) public class RecipeStepTest {
  @Rule public IntentsTestRule<StepsActivity> activityTestRule =
      new IntentsTestRule<>(StepsActivity.class, true, false);

  private StepsContract.Presenter presenter = new StepsPresenter();

  private StepsContract.View view;

  private int RECIPE_ID = 3;
  private int STEP_POS = 4;

  private Recipe RECIPE;
  private List<Step> STEP_LIST;

  @Before public void setUp() throws Throwable {
    PresenterProvider spyPresenterProvider = Mockito.spy(PresenterProvider.class);
    Mockito.when(spyPresenterProvider.provideSteps()).thenReturn(presenter);

    App.get().setPresenterProvider(spyPresenterProvider);

    activityTestRule.launchActivity(new Intent().putExtra(StepsActivity.KEY_RECIPE_ID, RECIPE_ID)
        .putExtra(StepsActivity.KEY_STEP_ID, STEP_POS));

    view = presenter.getView();

    RECIPE = RecipeFakeData.getRecipeById(RECIPE_ID);
    assert RECIPE != null;
    STEP_LIST = RECIPE.getSteps();

    activityTestRule.runOnUiThread(() -> view.showStep(STEP_LIST.get(STEP_POS), null));
  }

  @Test public void testHideBack() throws Throwable {
    activityTestRule.runOnUiThread(() -> view.setBackVisibility(false));
    onView(withId(R.id.back_btn)).check(matches(not(isDisplayed())));
  }

  @Test public void testHideNext() throws Throwable {
    activityTestRule.runOnUiThread(() -> view.setNextVisibility(false));
    onView(withId(R.id.next_btn)).check(matches(not(isDisplayed())));
  }

  @Test public void testShownBack() throws Throwable {
    onView(withId(R.id.back_btn)).check(matches(isDisplayed()));
  }

  @Test public void testShownNext() throws Throwable {
    onView(withId(R.id.next_btn)).check(matches(isDisplayed()));
  }

  @Test public void testNavigationText() {
    onView(withId(R.id.step_nav_num)).check(matches(withText(STEP_POS + "/" + STEP_LIST.size())));
  }

  @Test public void testDescription() {
    onView(withId(R.id.step_text)).check(
        matches(withText(STEP_LIST.get(STEP_POS).getDescription())));
  }
}

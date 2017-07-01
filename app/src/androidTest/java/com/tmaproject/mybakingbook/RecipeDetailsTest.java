package com.tmaproject.mybakingbook;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import com.tmaproject.mybakingbook.Utils.ResponsiveUi;
import com.tmaproject.mybakingbook.actions.NestedScrollToAction;
import com.tmaproject.mybakingbook.data.RecipeFakeData;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsActivity;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsContract;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsPresenter;
import com.tmaproject.mybakingbook.presenter.Steps.StepsActivity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by TarekLMA on 6/30/17.
 * tarekkma@gmail.com
 */

@RunWith(AndroidJUnit4.class) public class RecipeDetailsTest {
  @Rule public IntentsTestRule<RecipeDetailsActivity> activityTestRule =
      new IntentsTestRule<>(RecipeDetailsActivity.class, true, false);

  private RecipeDetailsContract.Presenter presenter = new RecipeDetailsPresenter();

  private RecipeDetailsContract.View view;

  private int RECIPE_ID = 2;
  private int TESTING_INGREDIENT_POS = 3;
  private int TESTING_STEP_VID_POS = 0;
  private int TESTING_STEP_POS = 3;

  private Recipe RECIPE;
  private Step STEP;
  private Step STEP_VID;
  private Ingredient INGREDIENT;

  public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher,
      final int childPosition) {
    return new TypeSafeMatcher<View>() {
      @Override public void describeTo(Description description) {
        description.appendText("with " + childPosition + " child view of type parentMatcher");
      }

      @Override public boolean matchesSafely(View view) {
        if (!(view.getParent() instanceof ViewGroup)) {
          return parentMatcher.matches(view.getParent());
        }

        ViewGroup group = (ViewGroup) view.getParent();
        return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition)
            .equals(view);
      }
    };
  }

  @Before public void setUp() throws Throwable {
    PresenterProvider spyPresenterProvider = Mockito.spy(PresenterProvider.class);
    Mockito.when(spyPresenterProvider.provideRecipeDetails()).thenReturn(presenter);

    App.get().setPresenterProvider(spyPresenterProvider);

    activityTestRule.launchActivity(
        new Intent().putExtra(RecipeDetailsActivity.KEY_RECIPE_ID, RECIPE_ID));

    view = presenter.getView();

    RECIPE = RecipeFakeData.getRecipeById(RECIPE_ID);

    assert RECIPE != null;
    INGREDIENT = RECIPE.getIngredients().get(TESTING_INGREDIENT_POS);
    STEP_VID = RECIPE.getSteps().get(TESTING_STEP_VID_POS);
    STEP = RECIPE.getSteps().get(TESTING_STEP_POS);

    activityTestRule.runOnUiThread(() -> {
      view.showRecipeDetails(RECIPE);
      view.showIngredients(RECIPE.getIngredients());
      view.showSteps(RECIPE.getSteps());
    });
  }

  @Test public void testIngredientItemDisplayed() {

    onView(allOf(
        isDescendantOfA(nthChildOf(withId(R.id.ingredient_layout_list), TESTING_INGREDIENT_POS)),
        withId(R.id.item_name))).check(matches(withText(INGREDIENT.getIngredient())));
  }

  @Test public void testStepItemDisplayed() {
    onView(allOf(isDescendantOfA(nthChildOf(withId(R.id.steps_layout_list), TESTING_STEP_VID_POS)),
        withId(R.id.item_name))).check(matches(withText(STEP_VID.getShortDescription())));
  }

  @Test public void testVideoIconShouldVisible() {
    onView(allOf(isDescendantOfA(nthChildOf(withId(R.id.steps_layout_list), TESTING_STEP_VID_POS)),
        withId(R.id.video))).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
  }

  @Test public void testVideoIconShouldInvisible() {
    onView(allOf(isDescendantOfA(nthChildOf(withId(R.id.steps_layout_list), TESTING_STEP_POS)),
        withId(R.id.video))).check(
        matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
  }

  @Test public void testOnStepClickPhone() {
    Assume.assumeFalse(ResponsiveUi.isTablet());

    onView(nthChildOf(withId(R.id.steps_layout_list), TESTING_STEP_POS)).perform(
        new NestedScrollToAction(), click());

    intending(allOf(hasExtra(is(StepsActivity.KEY_STEP_ID), is(STEP.getIndex())),
        hasExtra(is(StepsActivity.KEY_RECIPE_ID), is(STEP.getRecipeId()))));
  }

  @Test public void testOnStepClickTablet() {
    Assume.assumeTrue(ResponsiveUi.isTablet());

    onView(nthChildOf(withId(R.id.steps_layout_list), TESTING_STEP_POS)).perform(
        new NestedScrollToAction(), click());

    onView(withId(R.id.step_text)).check(matches(withText(STEP.getDescription())));
  }
}

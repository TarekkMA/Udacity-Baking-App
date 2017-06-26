package com.tmaproject.mybakingbook.data.source.recipes.local;

import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.data.source.recipes.RecipesDataSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/22/17.
 */

public class RecipesLocalDataSource implements RecipesDataSource {

  RecipesDao dao;

  public RecipesLocalDataSource() {
    dao = App.get().database.recipesDao();
  }

  @Override public Single<List<Recipe>> getRecipes() {
    return Single.fromCallable(() -> {

      List<Recipe> recipes = dao.getAllRecipes();
      for (Recipe recipe : recipes) {
        int recipeId = recipe.getId();

        List<Ingredient> ingredients = dao.getAllIngredients(recipeId);
        List<Step> steps = dao.getAllSteps(recipeId);

        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
      }
      return recipes;

    });
  }

  @Override public Single<List<Recipe>> getRecipeIngredients(int recipeId) {
    return null;
  }

  @Override public Single<List<Recipe>> getRecipeSteps(int recipeId) {
    return null;
  }

  @Override public void saveRecipes(List<Recipe> recipeList) {

    dao.deleteAll();

    dao.insertRecipes(recipeList);

    for (Recipe recipe : recipeList) {
      for (Ingredient ingredient : recipe.getIngredients()) {
        ingredient.setRecipeId(recipe.getId());
      }
      for (Step step : recipe.getSteps()) {
        step.setRecipeId(recipe.getId());
      }
      dao.insertIngredients(recipe.getIngredients());
      dao.insertSteps(recipe.getSteps());
    }
  }
}

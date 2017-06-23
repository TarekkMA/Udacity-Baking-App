package com.tmaproject.mybakingbook.model.source.recipes.local;

import com.tmaproject.mybakingbook.model.pojo.Recipe;
import com.tmaproject.mybakingbook.model.source.recipes.RecipesDataSource;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by tarekkma on 6/22/17.
 */

public class RecipesLocalDataSource implements RecipesDataSource {

  @Override public Single<List<Recipe>> getRecipes() {
    return null;
  }

  @Override public Single<List<Recipe>> getRecipeIngredients(int recipeId) {
    return null;
  }

  @Override public Single<List<Recipe>> getRecipeSteps(int recipeId) {
    return null;
  }
}

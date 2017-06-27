package com.tmaproject.mybakingbook.data.source.recipes.remote;

import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.data.source.recipes.RecipesDataSource;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by tarekkma on 6/22/17.
 */

public class RecipesRemoteDataSource implements RecipesDataSource {

  @Override public Single<List<Recipe>> getRecipes() {
    return RecipesService.createService().getRecipeList();
  }

  @Override public Single<Recipe> getRecipe(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override public Single<List<Ingredient>> getRecipeIngredients(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override public Single<List<Step>> getRecipeSteps(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override public void saveRecipes(List<Recipe> recipeList) {
    throw new UnsupportedOperationException("not implemented");
  }
}

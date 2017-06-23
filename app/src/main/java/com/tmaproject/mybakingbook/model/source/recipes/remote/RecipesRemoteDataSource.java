package com.tmaproject.mybakingbook.model.source.recipes.remote;

import com.tmaproject.mybakingbook.Utils.RxUtils;
import com.tmaproject.mybakingbook.model.pojo.Recipe;
import com.tmaproject.mybakingbook.model.source.recipes.RecipesDataSource;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by tarekkma on 6/22/17.
 */

public class RecipesRemoteDataSource implements RecipesDataSource {

  @Override public Single<List<Recipe>> getRecipes() {
    return RecipesService.createService().getRecipeList();
  }

  @Override public Single<List<Recipe>> getRecipeIngredients(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override public Single<List<Recipe>> getRecipeSteps(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }
}

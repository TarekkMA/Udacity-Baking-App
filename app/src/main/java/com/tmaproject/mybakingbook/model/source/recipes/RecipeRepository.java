package com.tmaproject.mybakingbook.model.source.recipes;

import com.tmaproject.mybakingbook.Utils.RxUtils;
import com.tmaproject.mybakingbook.model.pojo.Recipe;
import com.tmaproject.mybakingbook.model.source.recipes.local.RecipesLocalDataSource;
import com.tmaproject.mybakingbook.model.source.recipes.remote.RecipesRemoteDataSource;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by tarekkma on 6/23/17.
 */

public class RecipeRepository implements RecipesDataSource {

  RecipesLocalDataSource mLocalDataSource;
  RecipesRemoteDataSource mRemoteDataSource;

  public RecipeRepository(RecipesLocalDataSource mLocalDataSource,
      RecipesRemoteDataSource mRemoteDataSource) {
    this.mLocalDataSource = mLocalDataSource;
    this.mRemoteDataSource = mRemoteDataSource;
  }

  @Override public Single<List<Recipe>> getRecipes() {
    return mRemoteDataSource.getRecipes().compose(RxUtils.applySchedulers());
  }

  @Override public Single<List<Recipe>> getRecipeIngredients(int recipeId) {
    return null;
  }

  @Override public Single<List<Recipe>> getRecipeSteps(int recipeId) {
    return null;
  }
}

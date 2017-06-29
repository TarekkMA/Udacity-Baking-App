package com.tmaproject.mybakingbook.domain;

import com.tmaproject.mybakingbook.Utils.RxUtils;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.source.images.ImageRepository;
import com.tmaproject.mybakingbook.data.source.recipes.RecipeRepository;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesLocalDataSource;
import com.tmaproject.mybakingbook.data.source.recipes.remote.RecipesRemoteDataSource;
import io.reactivex.Single;

/**
 * Created by tarekkma on 6/27/17.
 */

public class GetRecipeInteractor {

  private RecipeRepository mRecipeRepository;

  public GetRecipeInteractor() {
    this.mRecipeRepository =
        new RecipeRepository(new RecipesLocalDataSource(), new RecipesRemoteDataSource());
  }

  public Single<Recipe> getRecipeDetails(int recipeId) {
    return mRecipeRepository.getRecipe(recipeId).compose(RxUtils.applySchedulersSingle());
  }
}

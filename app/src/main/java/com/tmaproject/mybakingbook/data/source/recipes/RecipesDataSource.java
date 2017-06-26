package com.tmaproject.mybakingbook.data.source.recipes;

import com.tmaproject.mybakingbook.data.pojo.Recipe;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by tarekkma on 6/22/17.
 */

public interface RecipesDataSource {

  Single<List<Recipe>> getRecipes();

  Single<List<Recipe>> getRecipeIngredients(int recipeId);

  Single<List<Recipe>> getRecipeSteps(int recipeId);

  void saveRecipes(List<Recipe> recipeList);

}

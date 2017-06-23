package com.tmaproject.mybakingbook.model.source.recipes;

import com.tmaproject.mybakingbook.model.pojo.Recipe;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by tarekkma on 6/22/17.
 */

public interface RecipesDataSource {

  Single<List<Recipe>> getRecipes();

  Single<List<Recipe>> getRecipeIngredients(int recipeId);

  Single<List<Recipe>> getRecipeSteps(int recipeId);

}

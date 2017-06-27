package com.tmaproject.mybakingbook.data.source.recipes;

import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by tarekkma on 6/22/17.
 */

public interface RecipesDataSource {

  Single<List<Recipe>> getRecipes();

  Single<Recipe> getRecipe(int recipeId);

  Single<List<Ingredient>> getRecipeIngredients(int recipeId);

  Single<List<Step>> getRecipeSteps(int recipeId);

  void saveRecipes(List<Recipe> recipeList);

}

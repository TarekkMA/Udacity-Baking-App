package com.tmaproject.mybakingbook.data.source.recipes.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import io.reactivex.Flowable;
import java.util.List;

import static com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract.INGREDIENT_TABLE_NAME;
import static com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract.RECIPES_TABLE_NAME;
import static com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract.STEP_TABLE_NAME;

/**
 * Created by tarekkma on 6/24/17.
 */

@Dao
public interface RecipesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertRecipes(List<Recipe> recipeList);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertRecipe(Recipe recipe);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertIngredients(List<Ingredient> ingredientList);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertSteps(List<Step> stepList);


  @Query("Select * FROM " + RECIPES_TABLE_NAME)
  List<Recipe> getAllRecipes();

  @Query("Select * FROM " + INGREDIENT_TABLE_NAME + " WHERE recipeId=:recipeId")
  List<Ingredient> getAllIngredients(int recipeId);

  @Query("Select * FROM " + STEP_TABLE_NAME + " WHERE recipeId=:recipeId")
  List<Step> getAllSteps(int recipeId);



  @Query("DELETE FROM " + RECIPES_TABLE_NAME)
  void deleteAll();





}

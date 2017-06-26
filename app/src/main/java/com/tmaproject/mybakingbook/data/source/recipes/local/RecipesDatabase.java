package com.tmaproject.mybakingbook.data.source.recipes.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;

/**
 * Created by tarekkma on 6/24/17.
 */

@Database(entities = { Recipe.class, Ingredient.class, Step.class} ,version = 2)
public abstract  class RecipesDatabase extends RoomDatabase{
  public abstract RecipesDao recipesDao();
}

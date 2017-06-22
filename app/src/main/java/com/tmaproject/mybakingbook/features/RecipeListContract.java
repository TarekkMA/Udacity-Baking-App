package com.tmaproject.mybakingbook.features;

/**
 * Created by tarekkma on 6/22/17.
 */

public interface RecipeListContract {

  interface View{

    void showLoading();
    void hideLoading();

    void showError(String error);
    void hideError();

    void showItems();

  }

  interface Presenter{
    void getRecipeList();
    void openRecipeDetails(int recipeId);
  }

}

package com.tmaproject.mybakingbook.presenter.RecipeList;

import com.tmaproject.mybakingbook.presenter.BaseContract;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import java.util.List;

/**
 * Created by tarekkma on 6/22/17.
 */

public interface RecipeListContract  {

  interface View extends BaseContract.BaseView {

    void showLoading();
    void hideLoading();

    void showError(String error);
    void hideError();

    void showItems(List<Recipe> recipeList);

  }

  interface Presenter extends BaseContract.BasePresenter{
    void getRecipeList();
    void openRecipeDetails(int recipeId);
  }

}

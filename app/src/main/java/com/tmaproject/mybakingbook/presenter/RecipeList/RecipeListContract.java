package com.tmaproject.mybakingbook.presenter.RecipeList;

import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.presenter.BaseContract;
import java.util.List;

/**
 * Created by TarekLMA on 6/22/17.
 * tarekkma@gmail.com
 */

public interface RecipeListContract  {

  interface View extends BaseContract.BaseView<Presenter> {

    void showLoading();
    void hideLoading();

    void showError(String error);
    void hideError();

    void showItems(List<Recipe> recipeList);

    Presenter getPresenter();
  }

  interface Presenter extends BaseContract.BasePresenter<View> {
    void getRecipeList();
    void syncData();
  }

}

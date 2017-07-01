package com.tmaproject.mybakingbook;

import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsContract;
import com.tmaproject.mybakingbook.presenter.RecipeDetails.RecipeDetailsPresenter;
import com.tmaproject.mybakingbook.presenter.RecipeList.RecipeListContract;
import com.tmaproject.mybakingbook.presenter.RecipeList.RecipeListPresenter;
import com.tmaproject.mybakingbook.presenter.Steps.StepsContract;
import com.tmaproject.mybakingbook.presenter.Steps.StepsPresenter;

/**
 * Created by TarekLMA on 7/1/17.
 * tarekkma@gmail.com
 */

//This is a temp solution until I learn how to use dagger
public class PresenterProvider {
  public RecipeListContract.Presenter provideRecipeList() {
    return new RecipeListPresenter();
  }

  public RecipeDetailsContract.Presenter provideRecipeDetails() {
    return new RecipeDetailsPresenter();
  }

  public StepsContract.Presenter provideSteps() {
    return new StepsPresenter();
  }
}

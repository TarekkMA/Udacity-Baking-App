package com.tmaproject.mybakingbook.presenter.RecipeDetails;

import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.BaseContract;
import java.util.List;

/**
 * Created by tarekkma on 6/26/17.
 */

public interface RecipeDetailsContract {
  interface View extends BaseContract.BaseView{
    void showRecipeDetails(Recipe recipe);
    void showIngredients(List<Ingredient> ingredientList);
    void showSteps(List<Step> stepList);
    void showMessage(String message);
  }
  interface Presenter extends BaseContract.BasePresenter {
    void loadRecipeDetails();
  }
}

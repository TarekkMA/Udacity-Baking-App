package com.tmaproject.mybakingbook.presenter.RecipeDetails;

import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.source.recipes.RecipeRepository;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesLocalDataSource;
import com.tmaproject.mybakingbook.data.source.recipes.remote.RecipesRemoteDataSource;
import com.tmaproject.mybakingbook.domain.GetRecipeDetailsInteractor;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/27/17.
 */

class RecipeDetailsPresenter implements RecipeDetailsContract.Presenter {

  CompositeDisposable compositeDisposable = new CompositeDisposable();

  GetRecipeDetailsInteractor interactor;

  RecipeDetailsContract.View view;
  int recipeId;

  public RecipeDetailsPresenter(RecipeDetailsContract.View view, int recipeId) {
    this.view = view;
    this.recipeId = recipeId;
    interactor = new GetRecipeDetailsInteractor();
  }

  @Override public void subscribe() {
    loadRecipeDetails();
  }

  @Override public void unsubscribe() {
    compositeDisposable.clear();
  }

  @Override public void loadRecipeDetails() {
    compositeDisposable.add(
        interactor.getRecipeDetails(recipeId).subscribeWith(new DisposableSingleObserver<Recipe>(){

          @Override public void onSuccess(@NonNull Recipe recipe) {
            view.showRecipeDetails(recipe);
            view.showIngredients(recipe.getIngredients());
            view.showSteps(recipe.getSteps());
          }

          @Override public void onError(@NonNull Throwable e) {
            Timber.e(e);
            view.showMessage(e.getMessage());
          }
        })
    );
  }
}


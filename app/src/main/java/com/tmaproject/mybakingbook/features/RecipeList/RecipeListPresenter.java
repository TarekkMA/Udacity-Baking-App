package com.tmaproject.mybakingbook.features.RecipeList;

import com.tmaproject.mybakingbook.model.pojo.Recipe;
import com.tmaproject.mybakingbook.model.source.recipes.RecipeRepository;
import com.tmaproject.mybakingbook.model.source.recipes.local.RecipesLocalDataSource;
import com.tmaproject.mybakingbook.model.source.recipes.remote.RecipesRemoteDataSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;

/**
 * Created by tarekkma on 6/23/17.
 */

public class RecipeListPresenter implements RecipeListContract.Presenter {

  CompositeDisposable mCompositeDisposable =  new CompositeDisposable();

  RecipeRepository mRecipeRepository;

  RecipeListContract.View mView;

  public RecipeListPresenter(RecipeListContract.View view) {
    RecipesLocalDataSource source = new RecipesLocalDataSource();
    mRecipeRepository =
        new RecipeRepository(new RecipesLocalDataSource(), new RecipesRemoteDataSource());
    mView = view;
  }

  @Override public void subscribe() {
    getRecipeList();
  }

  @Override public void unsubscribe() {
    mCompositeDisposable.clear();
  }

  @Override public void getRecipeList() {
    mView.hideLoading();
    mCompositeDisposable.add(
        mRecipeRepository.getRecipes().subscribeWith(new DisposableSingleObserver<List<Recipe>>() {
          @Override public void onSuccess(@NonNull List<Recipe> recipes) {
            mView.hideLoading();
            mView.showItems(recipes);
          }

          @Override public void onError(@NonNull Throwable e) {
            mView.showError(e.getMessage());
          }
        }));
  }

  @Override public void openRecipeDetails(int recipeId) {

  }
}

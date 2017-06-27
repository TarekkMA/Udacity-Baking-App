package com.tmaproject.mybakingbook.presenter.RecipeList;

import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.domain.GetRecipeListInteractor;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/23/17.
 */

public class RecipeListPresenter implements RecipeListContract.Presenter {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private GetRecipeListInteractor interactor;

  private RecipeListContract.View mView;

  public RecipeListPresenter(RecipeListContract.View view) {
    interactor = new GetRecipeListInteractor();
    mView = view;
  }

  @Override public void subscribe() {
    Timber.d("Presenter Subscribing");
    getRecipeList();
  }

  @Override public void unsubscribe() {
    Timber.d("Presenter Unsubscribing");
    mCompositeDisposable.clear();
  }

  @Override public void getRecipeList() {
    Timber.d("Getting Recipe List");
    mView.showLoading();
    mCompositeDisposable.add(
        interactor.getRecipeList().subscribeWith(new DisposableSingleObserver<List<Recipe>>() {
          @Override public void onSuccess(@NonNull List<Recipe> recipes) {
            mView.hideLoading();
            mView.showItems(recipes);
          }

          @Override public void onError(@NonNull Throwable e) {
            Timber.e(e);
            mView.hideLoading();
            mView.showError(e.getMessage());
          }
        }));
  }

  @Override public void syncData() {
    App.get().preferencesUtils.setDataUpToDate(false);
    getRecipeList();
  }
}

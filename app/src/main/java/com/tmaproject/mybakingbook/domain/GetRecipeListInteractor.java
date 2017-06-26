package com.tmaproject.mybakingbook.domain;

import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.Utils.RxUtils;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.source.images.ImageRepository;
import com.tmaproject.mybakingbook.data.source.recipes.RecipeRepository;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesLocalDataSource;
import com.tmaproject.mybakingbook.data.source.recipes.remote.RecipesRemoteDataSource;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Random;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/23/17.
 */

public class GetRecipeListInteractor {
  private RecipeRepository mRecipeRepository;
  private ImageRepository mImageRepository;

  public GetRecipeListInteractor() {
    this.mRecipeRepository =
        new RecipeRepository(new RecipesLocalDataSource(), new RecipesRemoteDataSource());
    this.mImageRepository = new ImageRepository();
  }

  public Single<List<Recipe>> getRecipeList() {

    if(mRecipeRepository.isDataUpToDate()){
      Timber.d("Data is up to date. Retrieving from local source");
      return mRecipeRepository.getRecipes().compose(RxUtils.applySchedulersSingle());
    }

    return mRecipeRepository.getRecipes()
        .flattenAsObservable(recipes -> recipes)
        .flatMap(r -> Observable.just(r).subscribeOn(Schedulers.io()).map(recipe -> {
          if (recipe.getImage() == null || recipe.getImage().isEmpty()) {
            Timber.d("Requesting for '%s' image from the repository", recipe.getName());
            String imgUrl = mImageRepository.getImage(recipe.getName()).blockingGet();
            recipe.setImage(imgUrl);
          }
          return recipe;
        }))
        .toList()
        .doOnSuccess(recipes -> {
          Timber.d("Saving recipes list into the database");
          mRecipeRepository.saveRecipes(recipes);
          App.get().preferencesUtils.setDataUpToDate(true);
        })
        .compose(RxUtils.applySchedulersSingle());
  }
}

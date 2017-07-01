package com.tmaproject.mybakingbook.presenter.Steps;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.BuildConfig;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.domain.GetRecipeInteractor;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/28/17.
 */

public class StepsPresenter implements StepsContract.Presenter {

  CompositeDisposable compositeDisposable = new CompositeDisposable();

  private StepsContract.View view;
  private int recipeId;

  private Recipe recipe;

  private GetRecipeInteractor interactor;
  private int currentStep = -1;

  public StepsPresenter(StepsContract.View view, int recipeId,int currentStep) {
    interactor = new GetRecipeInteractor();
    this.view = view;
    this.recipeId = recipeId;
    this.currentStep = currentStep;
  }

  private void showStepAt(int pos){

    view.setBackVisibility(pos!=0);
    view.setNextVisibility(pos!=recipe.getSteps().size());
    view.setStepNum(currentStep+"/"+recipe.getSteps().size());

    Step step = recipe.getSteps().get(pos);

    SimpleExoPlayer exoPlayer = null;


    if(!TextUtils.isEmpty(step.getVideoURL())){
      exoPlayer = ExoPlayerFactory.newSimpleInstance(
          App.get()
          ,new DefaultTrackSelector());

      Uri uri = Uri.parse(step.getVideoURL());


      String userAgent = Util.getUserAgent(App.get(), "Recipe Step");

      OkHttpDataSource.Factory httpDataSource = new OkHttpDataSourceFactory(App.get().client,userAgent,null);

      MediaSource mediaSource = new ExtractorMediaSource(uri,httpDataSource,new DefaultExtractorsFactory(),null,null);

      exoPlayer.prepare(mediaSource);
      exoPlayer.setPlayWhenReady(true);
    }
    view.showStep(step,exoPlayer);

  }

  @Override public void subscribe() {
    loadRecipeDetails();
  }

  @Override public void unsubscribe() {
    compositeDisposable.clear();
  }

  @Override public int getCurrentInedex() {
    return currentStep;
  }

  @Override public void showStep(Step step) {
    currentStep = step.getIndex();
    showStepAt(currentStep);
  }

  @Override public void loadRecipeDetails() {
    compositeDisposable.add(interactor.getRecipeDetails(recipeId).subscribeWith(
        new DisposableSingleObserver<Recipe>() {
          @Override public void onSuccess(@NonNull Recipe r) {
            recipe = r;
            showStepAt(currentStep);
          }

          @Override public void onError(@NonNull Throwable e) {
            Timber.e(e);
            view.showMessage(e.getMessage());
          }
        }));
  }

  @Override public void showNextStep() {
    currentStep++;
    showStepAt(currentStep);
  }

  @Override public void showPreviousStep() {
    currentStep--;
    showStepAt(currentStep);
  }
}

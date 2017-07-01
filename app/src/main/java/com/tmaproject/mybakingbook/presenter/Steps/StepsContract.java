package com.tmaproject.mybakingbook.presenter.Steps;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.BaseContract;

/**
 * Created by TarekLMA on 6/26/17.
 * tarekkma@gmail.com
 */

public interface StepsContract {

  interface View extends BaseContract.BaseView<Presenter> {
    void showStep(Step step,SimpleExoPlayer player);
    void setNextVisibility(boolean isVisible);
    void setBackVisibility(boolean isVisible);
    void setStepNum(String stepNum);
    void showMessage(String message);
  }

  interface Presenter extends BaseContract.BasePresenter<View> {
    int getCurrentInedex();

    void setCurrentIndex(int index);

    void setRecipeId(int recipeId);
    void showStep(Step step);
    void loadRecipeDetails();
    void showNextStep();
    void showPreviousStep();
  }
}

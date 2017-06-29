package com.tmaproject.mybakingbook.presenter.Steps;

import android.os.Bundle;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.BaseContract;

/**
 * Created by tarekkma on 6/26/17.
 */

public interface StepsContract {

  interface View extends BaseContract.BaseView{
    void showStep(Step step,SimpleExoPlayer player);
    void setNextVisibility(boolean isVisible);
    void setBackVisibility(boolean isVisible);
    void setStepNum(String stepNum);
    void showMessage(String message);
  }
  interface Presenter extends BaseContract.BasePresenter {
    int getCurrentInedex();
    void showStep(Step step);
    void loadRecipeDetails();
    void showNextStep();
    void showPreviousStep();
  }
}

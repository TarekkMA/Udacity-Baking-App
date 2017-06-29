package com.tmaproject.mybakingbook.presenter;

import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.pojo.Step;

/**
 * Created by tarekkma on 6/29/17.
 */

public interface Callbacks {
  interface AdapterRecipeClicked{
    void onClick(Recipe recipe);
  }
  interface StepClicked {
    void onClick(Step step);
  }
}

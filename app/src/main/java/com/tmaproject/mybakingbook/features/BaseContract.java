package com.tmaproject.mybakingbook.features;

/**
 * Created by tarekkma on 6/23/17.
 */

public interface BaseContract {
  interface BaseView{

  }
  interface BasePresenter{
    void subscribe();
    void unsubscribe();
  }
}

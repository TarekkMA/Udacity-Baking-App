package com.tmaproject.mybakingbook.presenter;

/**
 * Created by TarekKMA on 6/23/17.
 * tarekkma@gmail.com
 */

public interface BaseContract {
  interface BaseView<P> {
    void setPresenter(P presenter);
  }

  interface BasePresenter<V> {
    V getView();

    void subscribe(V view);
    void unsubscribe();
  }
}

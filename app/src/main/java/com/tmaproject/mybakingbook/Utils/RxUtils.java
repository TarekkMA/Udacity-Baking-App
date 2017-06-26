package com.tmaproject.mybakingbook.Utils;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tarekkma on 6/23/17.
 */

public class RxUtils {
  public static <T> SingleTransformer<T, T> applySchedulersSingle() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
  public static <T> ObservableTransformer<T, T> applySchedulersObservable() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}

package com.tmaproject.mybakingbook.model.source.images;

import com.tmaproject.mybakingbook.App;
import io.reactivex.Single;
import okhttp3.OkHttpClient;

/**
 * Created by tarekkma on 6/23/17.
 */

public class ImageRepository implements ImageDataSource {

  OkHttpClient okHttpClient;

  public ImageRepository() {
    this.okHttpClient = App.get().client;
  }

  @Override public Single<String> getImage(String recipeName) {
    return null;
  }
}

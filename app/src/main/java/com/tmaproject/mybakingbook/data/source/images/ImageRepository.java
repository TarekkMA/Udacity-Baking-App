package com.tmaproject.mybakingbook.data.source.images;

import com.tmaproject.mybakingbook.App;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/23/17.
 */

public class ImageRepository implements ImageDataSource {

  OkHttpClient okHttpClient;

  public ImageRepository() {
    this.okHttpClient = App.get().client;
  }

  @Override public Single<String> getImage(String recipeName) {
    String urlFromPrefs = App.get().preferencesUtils.getRecipeImage(recipeName);
    if(urlFromPrefs == null || urlFromPrefs.isEmpty()){
      Timber.d("Searching Google Images for %s",recipeName);
      return GoogleImageSearchAPI.getFirstImage(recipeName);
    }else{
      Timber.d("Image from cache %s",recipeName);
      return Single.just(urlFromPrefs);
    }
  }
}

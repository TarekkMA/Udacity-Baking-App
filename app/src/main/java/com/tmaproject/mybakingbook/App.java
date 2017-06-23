package com.tmaproject.mybakingbook;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tmaproject.mybakingbook.model.source.recipes.remote.RecipesRemoteDataSource;
import com.tmaproject.mybakingbook.model.source.recipes.remote.RecipesService;
import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/22/17.
 */

public class App extends Application {

  // singleton
  private static App INSTANCE;

  public OkHttpClient client;

  public static App get() {
    return INSTANCE;
  }

  @Override public void onCreate() {
    super.onCreate();

    INSTANCE = this;

    Timber.plant(new Timber.DebugTree());
    Stetho.initializeWithDefaults(this);
    client = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();

    RecipesService.init(client);
  }
}

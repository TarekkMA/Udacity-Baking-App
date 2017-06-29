package com.tmaproject.mybakingbook;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.exoplayer2.ExoPlayer;
import com.squareup.leakcanary.LeakCanary;
import com.tmaproject.mybakingbook.Utils.PreferencesUtils;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesDatabase;
import com.tmaproject.mybakingbook.data.source.recipes.remote.RecipesService;
import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/22/17.
 */

public class App extends Application {

  // singleton
  private static App INSTANCE;

  public OkHttpClient client = new OkHttpClient();

  public RecipesDatabase database;

  public PreferencesUtils preferencesUtils;

  public static App get() {
    return INSTANCE;
  }

  @Override public void onCreate() {
    super.onCreate();

    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);

    INSTANCE = this;

    Timber.plant(new Timber.DebugTree());
    Stetho.initializeWithDefaults(this);
    client = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();

    RecipesService.init(client);

    database = Room.databaseBuilder(this,RecipesDatabase.class, DatabaseContract.DATABASE_NAME).build();

    preferencesUtils = new PreferencesUtils(this);
  }


}

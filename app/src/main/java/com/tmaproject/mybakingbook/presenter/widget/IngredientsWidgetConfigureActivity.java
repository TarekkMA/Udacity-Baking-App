package com.tmaproject.mybakingbook.presenter.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.Utils.PreferencesUtils;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesDatabase;
import com.tmaproject.mybakingbook.domain.GetRecipeListInteractor;
import com.tmaproject.mybakingbook.presenter.Callbacks;
import com.tmaproject.mybakingbook.presenter.RecipeList.RecipeListAdapter;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;

/**
 * The configuration screen for the {@link IngredientsWidget IngredientsWidget} AppWidget.
 */
public class IngredientsWidgetConfigureActivity extends Activity implements
    Callbacks.AdapterRecipeClicked {

  int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

  CompositeDisposable compositeDisposable = new CompositeDisposable();

  public IngredientsWidgetConfigureActivity() {
    super();
  }

  @Override public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    // Set the result to CANCELED.  This will cause the widget host to cancel
    // out of the widget placement if the user presses the back button.
    setResult(RESULT_CANCELED);

    setContentView(R.layout.new_app_widget_configure);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

    // Find the widget id from the intent.
    Intent intent = getIntent();
    Bundle extras = intent.getExtras();
    if (extras != null) {
      mAppWidgetId =
          extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    // If this activity was started with an intent without an app widget ID, finish with an error.
    if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
      finish();
      return;
    }

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    RecipeListAdapter adapter = new RecipeListAdapter(this);
    recyclerView.setAdapter(adapter);
    compositeDisposable.add(new GetRecipeListInteractor().getRecipeList().subscribeWith(
        new DisposableSingleObserver<List<Recipe>>() {
          @Override public void onSuccess(@NonNull List<Recipe> recipes) {
            adapter.swapList(recipes);
          }

          @Override public void onError(@NonNull Throwable e) {
            Toast.makeText(IngredientsWidgetConfigureActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
          }
        }));
  }


  @Override protected void onPause() {
    super.onPause();
    compositeDisposable.clear();
  }

  @Override public void onClick(Recipe recipe) {
    final Context context = IngredientsWidgetConfigureActivity.this;

    App.get().preferencesUtils.setWidgetRecipeId(mAppWidgetId, recipe.getId());

    // It is the responsibility of the configuration activity to update the app widget


    RecipesDatabase database =
        Room.databaseBuilder(context, RecipesDatabase.class, DatabaseContract.DATABASE_NAME)
            .build();
    PreferencesUtils widgetPreferencesUtils =
        new PreferencesUtils(context, PreferencesUtils.WIDGET_PREFS_NAME);

    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
    IngredientsWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId, database,
        widgetPreferencesUtils.getWidgetRecipeId(mAppWidgetId));

    // Make sure we pass back the original appWidgetId
    Intent resultValue = new Intent();
    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
    setResult(RESULT_OK, resultValue);
    finish();
  }
}


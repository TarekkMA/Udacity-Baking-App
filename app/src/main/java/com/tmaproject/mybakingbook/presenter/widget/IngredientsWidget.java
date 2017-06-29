package com.tmaproject.mybakingbook.presenter.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.google.gson.Gson;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.Utils.PreferencesUtils;
import com.tmaproject.mybakingbook.Utils.RxUtils;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesDatabase;
import com.tmaproject.mybakingbook.data.source.recipes.local.RecipesLocalDataSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import org.parceler.Parcels;
import timber.log.Timber;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link IngredientsWidgetConfigureActivity
 * IngredientsWidgetConfigureActivity}
 */
public class IngredientsWidget extends AppWidgetProvider {

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    // There may be multiple widgets active, so update all of them
    RecipesDatabase database =
        Room.databaseBuilder(context, RecipesDatabase.class, DatabaseContract.DATABASE_NAME)
            .build();
    PreferencesUtils widgetPreferencesUtils =
        new PreferencesUtils(context, PreferencesUtils.WIDGET_PREFS_NAME);
    int recipeId;

    for (int appWidgetId : appWidgetIds) {
      recipeId = widgetPreferencesUtils.getWidgetRecipeId(appWidgetId);
      updateAppWidget(context, appWidgetManager, appWidgetId,database,recipeId);
    }

  }

  @Override public void onDeleted(Context context, int[] appWidgetIds) {
    // When the user deletes the widget, delete the preference associated with it.

  }

  @Override public void onEnabled(Context context) {
    // Enter relevant functionality for when the first widget is created
  }

  @Override public void onDisabled(Context context) {
    // Enter relevant functionality for when the last widget is disabled
  }

  static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId,
      RecipesDatabase database, int recipeId) {


    if(recipeId==-1)return;

    RecipesLocalDataSource localDataSource = new RecipesLocalDataSource(database.recipesDao());

    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

    localDataSource.getRecipe(recipeId)
        .compose(RxUtils.applySchedulersSingle())
        .subscribeWith(new DisposableSingleObserver<Recipe>() {
          @Override public void onSuccess(@NonNull Recipe recipe) {
            views.setTextViewText(R.id.appwidget_text, recipe.getName());

            Intent intent = new Intent(context, ListWidgetService.class);

            intent.putExtra(ListWidgetService.KEY_RECIPE, new Gson().toJson(recipe));

            views.setRemoteAdapter(R.id.appwidget_list, intent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
            dispose();
          }

          @Override public void onError(@NonNull Throwable e) {
            Timber.e(e);
            dispose();
          }
        });
  }
}


package com.tmaproject.mybakingbook.presenter.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.google.gson.Gson;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;

/**
 * Created by tarekkma on 6/29/17.
 */
public class ListWidgetService extends RemoteViewsService {
  public static final String KEY_RECIPE = "INGREDIENTS";

  @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
    return new RemoteViewFactory(getApplicationContext(),intent);
  }

  class RemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {


    private final Context context;
    private final Intent intent;
    private List<Ingredient> ingredients = new ArrayList<>();

    public RemoteViewFactory(Context context, Intent intent) {

      this.context = context;
      this.intent = intent;
    }

    @Override public void onCreate() {
      Recipe recipe = new Gson().fromJson(intent.getStringExtra(KEY_RECIPE), Recipe.class);
      ingredients = recipe.getIngredients();
    }

    @Override public void onDataSetChanged() {

    }

    @Override public void onDestroy() {
    }

    @Override public int getCount() {
      return ingredients.size();
    }

    @Override public RemoteViews getViewAt(int position) {

      Ingredient ingredient = ingredients.get(position);

      RemoteViews views =
          new RemoteViews(context.getPackageName(), R.layout.widget_item_ingredient);

      views.setTextViewText(R.id.quantity, String.valueOf(ingredient.getQuantity()));
      views.setTextViewText(R.id.unit, ingredient.getMeasure());
      views.setTextViewText(R.id.item_name, ingredient.getIngredient());

      return views;
    }

    @Override public RemoteViews getLoadingView() {
      return null;
    }

    @Override public int getViewTypeCount() {
      return 1;
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public boolean hasStableIds() {
      return true;
    }
  }
}
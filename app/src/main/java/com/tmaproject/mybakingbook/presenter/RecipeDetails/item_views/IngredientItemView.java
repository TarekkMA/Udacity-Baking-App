package com.tmaproject.mybakingbook.presenter.RecipeDetails.item_views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.data.pojo.Ingredient;
import com.tmaproject.mybakingbook.data.pojo.Step;

/**
 * Created by tarekkma on 6/28/17.
 */

public class IngredientItemView extends LinearLayout {
  @BindView(R.id.quantity)TextView quantity;
  @BindView(R.id.item_name)TextView name;
  @BindView(R.id.unit)TextView unit;

  public IngredientItemView(Context context) {
    super(context);


    View view = inflate(getContext(), R.layout.item_ingredient,this);
    ButterKnife.bind(this,view);

  }

  public View bind(Ingredient ingredient){
    quantity.setText(String.valueOf(ingredient.getQuantity()));
    name.setText(ingredient.getIngredient());
    unit.setText(ingredient.getMeasure());
    return this;
  }

}

package com.tmaproject.mybakingbook.presenter.RecipeList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.data.pojo.Recipe;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarekkma on 6/19/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.VH> {

  List<Recipe> recipeList = new ArrayList<>();

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {

    return new VH(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false));
  }

  @Override public void onBindViewHolder(VH holder, int position) {
    holder.bind(recipeList.get(position));
  }

  @Override public int getItemCount() {
    return recipeList.size();
  }

  public void swapList(List<Recipe> newRecipeList) {
    recipeList = newRecipeList;
    notifyDataSetChanged();
  }

  public static class VH extends RecyclerView.ViewHolder {

    @BindView(R.id.image_recipe_item) ImageView imageView;
    @BindView(R.id.text_recipe_item) TextView textView;
    @BindView(R.id.ingredient_count_text) TextView ingredientsCountText;
    @BindView(R.id.servings_count_text) TextView servingsCountText;
    @BindView(R.id.steps_count_text) TextView stepsCountText;

    public VH(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bind(final Recipe recipe) {
      textView.setText(recipe.getName());
      ingredientsCountText.setText(String.valueOf(recipe.getIngredients().size()));
      servingsCountText.setText(String.valueOf(recipe.getServings()));
      stepsCountText.setText(String.valueOf(recipe.getSteps().size()));
      Glide.with(itemView)
          .load(recipe.getImage())
          .into(imageView);
    }
  }
}

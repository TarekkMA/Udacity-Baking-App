package com.tmaproject.mybakingbook.presenter.RecipeDetails.item_views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.Callbacks;
import com.tmaproject.mybakingbook.presenter.Steps.StepsActivity;

/**
 * Created by tarekkma on 6/28/17.
 */

public class StepItemView extends LinearLayout {

  @BindView(R.id.number)TextView number;
  @BindView(R.id.item_name)TextView name;
  @BindView(R.id.video)ImageView videoIcon;

  private Callbacks.StepClicked callback;

  public StepItemView(Context context, Callbacks.StepClicked callback) {
    super(context);
    this.callback = callback;

    inflate(getContext(), R.layout.item_step,this);
    ButterKnife.bind(this,this);

  }

  public View bind(Step step){
    number.setText(String.valueOf(step.getIndex())+".");
    name.setText(step.getShortDescription());
    if(step.getVideoURL()==null || step.getVideoURL().isEmpty())
      videoIcon.setVisibility(INVISIBLE);
    if(callback!=null)setOnClickListener(v -> callback.onClick(step));
    return this;
  }


}

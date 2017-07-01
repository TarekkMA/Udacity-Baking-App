package com.tmaproject.mybakingbook.presenter.Steps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.squareup.picasso.Picasso;
import com.tmaproject.mybakingbook.R;
import com.tmaproject.mybakingbook.data.pojo.Step;
import com.tmaproject.mybakingbook.presenter.Callbacks;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment implements StepsContract.View ,Callbacks.StepClicked {

  private Unbinder unbinder;

  @BindView(R.id.video_player) SimpleExoPlayerView playerView;
  @BindView(R.id.step_image) ImageView stepImage;
  @BindView(R.id.step_text) TextView stepText;
  @BindView(R.id.back_btn) FloatingActionButton backBtn;
  @BindView(R.id.next_btn) FloatingActionButton nextBtn;
  @BindView(R.id.step_nav_num) TextView stepIndexText;

  private static final String KEY_RECIPE_ID = "RECIPE_ID";
  private static final String KEY_STEP_ID = "STEP_ID";

  StepsPresenter presenter;


  public StepsFragment() {

  }

  public static StepsFragment newInstance(int recipeId, int stepId) {
    Bundle args = new Bundle();
    StepsFragment fragment = new StepsFragment();
    args.putInt(KEY_RECIPE_ID, recipeId);
    args.putInt(KEY_STEP_ID, stepId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    outState.putInt(KEY_STEP_ID, presenter.getCurrentInedex());
    super.onSaveInstanceState(outState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_steps, container, false);
    unbinder = ButterKnife.bind(this, view);

    int stepId;
    if (savedInstanceState != null) {
      stepId = savedInstanceState.getInt(KEY_STEP_ID);
    } else {
      stepId = getArguments().getInt(KEY_STEP_ID);
    }
    int recipeId = getArguments().getInt(KEY_RECIPE_ID);

    presenter = new StepsPresenter(this, recipeId, stepId);

    nextBtn.setOnClickListener(v -> presenter.showNextStep());
    backBtn.setOnClickListener(v -> presenter.showPreviousStep());

    return view;
  }

  @Override public void onResume() {
    super.onResume();
    presenter.subscribe();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.unsubscribe();
    if(playerView.getPlayer() != null)playerView.getPlayer().release();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void showStep(Step step, @Nullable SimpleExoPlayer player) {
    if (playerView.getPlayer() != null) playerView.getPlayer().release();
    stepText.setText(step.getDescription());

    //Both video and image are unavailable
    if(player != null){
      stepImage.setVisibility(View.GONE);
      playerView.setPlayer(player);
      playerView.setVisibility(View.VISIBLE);
    }else if(!TextUtils.isEmpty(step.getThumbnailURL())){
      playerView.setVisibility(View.GONE);
      Picasso.with(getContext()).load(step.getThumbnailURL()).into(stepImage);
      stepImage.setVisibility(View.VISIBLE);
    }else{
      playerView.setVisibility(View.GONE);
      stepImage.setVisibility(View.GONE);
    }

  }

  @Override public void setNextVisibility(boolean isVisible) {
    nextBtn.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
  }

  @Override public void setBackVisibility(boolean isVisible) {
    backBtn.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
  }

  @Override public void setStepNum(String stepNum) {
    stepIndexText.setText(stepNum);
  }

  @Override public void showMessage(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public void onClick(Step step) {
    presenter.showStep(step);
  }
}

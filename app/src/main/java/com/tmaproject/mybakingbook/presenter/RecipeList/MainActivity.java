package com.tmaproject.mybakingbook.presenter.RecipeList;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tmaproject.mybakingbook.R;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

  @BindView(R.id.toolbar) Toolbar mToolbar;

  @BindView(R.id.main_activity_container)View containerView;

  private RecipeListContract.View recipeListFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);

    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    if(savedInstanceState == null) {
      recipeListFragment = RecipeListFragment.newInstance();

      getSupportFragmentManager().
          beginTransaction().replace(R.id.fragmentFrame, (Fragment) recipeListFragment).commit();
    }
  }

  @Override public void onBackPressed() {
    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
      mDrawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.nav_about:
        Snackbar.make(containerView,"A Nanodegree Project For Udacity Course",Snackbar.LENGTH_LONG).show();
        break;
      case R.id.nav_sourcecode:
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("https://github.com/TarekkMA/Udacity-Baking-App"));
        startActivity(browserIntent);
        break;
      case R.id.nav_sync:
        recipeListFragment.getPresenter().syncData();
        break;
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}

package com.tmaproject.mybakingbook.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.R;

/**
 * Created by tarekkma on 6/22/17.
 */

public class ResponsiveUi {
  public static boolean isTablet() {
    return App.get().getResources().getBoolean(R.bool.isTablet);
  }

  public static boolean isLandscape() {
    return App.get().getResources().getBoolean(R.bool.isLandscape);
  }

  public static int getCoulumnNumber() {
    if (isTablet()) {

      int itemWidthDp = 250;
      //TODO TEST THIS CODE
      double multiplier = (isTablet()) ? .5 : 1;
      DisplayMetrics displayMetrics = App.get().getResources().getDisplayMetrics();
      float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
      dpWidth *= multiplier;
      int res = (int) (dpWidth / itemWidthDp);

      return (res == 0) ? 1 : res;
    } else if (isLandscape()) {
      return 2;
    } else {
      return 1;
    }
  }
}

package com.tmaproject.mybakingbook.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import com.tmaproject.mybakingbook.R;

/**
 * Created by tarekkma on 6/22/17.
 */

public class ResponsiveUi {
  public static boolean isTablet(Context context) {
    return context.getResources().getBoolean(R.bool.isTablet);
  }

  public static boolean isLandscape(Context context) {
    return context.getResources().getBoolean(R.bool.isLandscape);
  }

  public static int getCoulumnNumber(Context context) {
    if (isTablet(context)) {

      int itemWidthDp = 250;
      //TODO TEST THIS CODE
      double multiplier = (isTablet(context)) ? .5 : 1;
      DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
      float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
      dpWidth *= multiplier;
      int res = (int) (dpWidth / itemWidthDp);

      return (res == 0) ? 1 : res;
    } else if (isLandscape(context)) {
      return 2;
    } else {
      return 1;
    }
  }
}

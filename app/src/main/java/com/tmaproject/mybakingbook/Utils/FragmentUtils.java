package com.tmaproject.mybakingbook.Utils;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by tarekkma on 6/22/17.
 */

public class FragmentUtils {
  public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment,
      @IdRes int frameId) {
    fragmentManager.beginTransaction().replace(frameId,fragment).commit();
  }
}

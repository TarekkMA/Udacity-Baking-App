package com.tmaproject.mybakingbook.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tarekkma on 6/25/17.
 */

public class PreferencesUtils {

  private  SharedPreferences imagePrefs;
  private static final String IMAGE_CACHE_PREFS_NAME = "image-cache-prefs";

  private  SharedPreferences appPrefs;
  private static final String APP_PREFS_NAME = "app-prefs";


  private static final String SYNC_Key = "SYNC_KEY";


  public PreferencesUtils(Context context){
    imagePrefs = context.getSharedPreferences(IMAGE_CACHE_PREFS_NAME,Context.MODE_PRIVATE);
    appPrefs = context.getSharedPreferences(APP_PREFS_NAME,Context.MODE_PRIVATE);
  }


  private String getString(String key,SharedPreferences preferences){
    return preferences.getString(key,"");
  }
  private void putString(String key,String value,SharedPreferences preferences){
    preferences.edit().putString(key,value).apply();
  }

  private boolean getBoolean(String key,SharedPreferences preferences){
    return preferences.getBoolean(key,false);
  }
  private void putBoolean(String key,boolean value,SharedPreferences preferences){
    preferences.edit().putBoolean(key,value).apply();
  }

  public String getRecipeImage(String recipeName){
    return getString(recipeName,imagePrefs);
  }

  public void saveRecipeImage(String recipeName,String imageUrl){
    putString(recipeName,imageUrl,imagePrefs);
  }

  public boolean getDataUpToDate(){
    return getBoolean(SYNC_Key,appPrefs);
  }

  public void setDataUpToDate(boolean state){
    putBoolean(SYNC_Key,state,appPrefs);
  }



}

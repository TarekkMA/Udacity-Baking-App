package com.tmaproject.mybakingbook.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tarekkma on 6/25/17.
 */

public class PreferencesUtils {

  private  SharedPreferences imagePrefs;
  public static final String IMAGE_CACHE_PREFS_NAME = "image-cache-prefs";

  private  SharedPreferences appPrefs;
  public static final String APP_PREFS_NAME = "app-prefs";

  private  SharedPreferences widgetPrefs;
  public static final String WIDGET_PREFS_NAME = "widget-prefs";


  private static final String SYNC_Key = "SYNC_KEY";

  private static final String WIDGET_PREFIX = "WIDGET_";


  public PreferencesUtils(Context context){
    imagePrefs = context.getSharedPreferences(IMAGE_CACHE_PREFS_NAME,Context.MODE_PRIVATE);
    appPrefs = context.getSharedPreferences(APP_PREFS_NAME,Context.MODE_PRIVATE);
    widgetPrefs = context.getSharedPreferences(WIDGET_PREFS_NAME,Context.MODE_PRIVATE);
  }

  public PreferencesUtils(Context context,String prefsName){
    switch (prefsName){
      case IMAGE_CACHE_PREFS_NAME:
        imagePrefs = context.getSharedPreferences(IMAGE_CACHE_PREFS_NAME,Context.MODE_PRIVATE);
        break;
      case APP_PREFS_NAME:
        appPrefs = context.getSharedPreferences(APP_PREFS_NAME,Context.MODE_PRIVATE);
        break;
      case WIDGET_PREFS_NAME:
        widgetPrefs = context.getSharedPreferences(WIDGET_PREFS_NAME,Context.MODE_PRIVATE);
        break;
    }
  }


  private String getString(String key,SharedPreferences preferences){
    return preferences.getString(key,"");
  }
  private void putString(String key,String value,SharedPreferences preferences){
    preferences.edit().putString(key,value).apply();
  }

  private int getInt(String key,SharedPreferences preferences){
    return preferences.getInt(key,-1);
  }
  private void putInt(String key,int value,SharedPreferences preferences){
    preferences.edit().putInt(key,value).apply();
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

  public void setWidgetRecipeId(int widgetId,int recipeId){
    putInt(WIDGET_PREFIX+widgetId,recipeId,widgetPrefs);
  }

  public int getWidgetRecipeId(int widgetId){
    return getInt(WIDGET_PREFIX+widgetId,widgetPrefs);
  }



}

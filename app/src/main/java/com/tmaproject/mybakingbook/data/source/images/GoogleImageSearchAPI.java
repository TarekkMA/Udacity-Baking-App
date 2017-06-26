package com.tmaproject.mybakingbook.data.source.images;

import android.content.Context;
import android.os.Handler;
import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.BuildConfig;
import com.tmaproject.mybakingbook.Utils.PreferencesUtils;
import io.reactivex.Single;
import java.io.IOException;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;

/**
 * Created by tarekkma on 6/19/17.
 */

public class GoogleImageSearchAPI {

  public static Single<String> getFirstImage(String query) {

    String url = HttpUrl.parse("https://www.googleapis.com/customsearch/v1")
        .newBuilder()
        .addQueryParameter("key", BuildConfig.GoogleApiKey)
        .addQueryParameter("cx", BuildConfig.GoogleSearchEngineId)
        .addQueryParameter("searchType", "image")
        .addQueryParameter("q", query)
        .build()
        .toString();

    Request request = new Request.Builder().url(url).build();

    return Single.fromCallable(() -> App.get().client.newCall(request).execute()).map(response -> {
      String string = getFirstImageFromJson(response.body().string());
      //cache the result
      App.get().preferencesUtils.saveRecipeImage(query,string);
      return string;
    });
  }

  private static String getFirstImageFromJson(String json) {
    try {
      JSONObject root = new JSONObject(json);
      JSONArray items = root.getJSONArray("items");
      return items.getJSONObject(0).getString("link");
    } catch (JSONException e) {
      Timber.e(e, json);
      return null;
    }
  }
}

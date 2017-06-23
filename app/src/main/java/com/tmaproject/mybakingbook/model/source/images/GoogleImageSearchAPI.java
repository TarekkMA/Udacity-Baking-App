package com.tmaproject.mybakingbook.model.source.images;

import android.content.Context;
import android.os.Handler;
import com.tmaproject.mybakingbook.App;
import com.tmaproject.mybakingbook.BuildConfig;
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



    public static void getFirstImage(final Context c, String query, final GoogleSearchCallback callback){
        HttpUrl urlBuilder = HttpUrl.parse("https://www.googleapis.com/customsearch/v1")
                .newBuilder()
                .addQueryParameter("key", BuildConfig.GoogleApiKey)
                .addQueryParameter("cx", BuildConfig.GoogleSearchEngineId)
                .addQueryParameter("searchType","image")
                .addQueryParameter("q",query).build();
        String url = urlBuilder.toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        App.get().client.newCall(request).enqueue(new Callback() {

            Handler mainHandler = new Handler(c.getMainLooper()); //Run on the main thread

            @Override
            public void onFailure(okhttp3.Call call, final IOException e) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Timber.e(e);
                        callback.searchFailed();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, final Response response) throws IOException {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(response.isSuccessful()){
                            String imgUrl = null;

                            try {
                                imgUrl = getFirstImageFromJson(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if(imgUrl != null)
                                callback.searchDone(imgUrl);
                            else
                                callback.searchFailed();
                        }else{
                            callback.searchFailed();
                            Timber.e("Failed");
                        }
                    }
                });
            }
        });
    }

    private static String getFirstImageFromJson(String json){
        try {
            JSONObject root = new JSONObject(json);
            JSONArray items = root.getJSONArray("items");
            return items.getJSONObject(0).getString("link");
        } catch (JSONException e) {
            Timber.e(e);
            return null;
        }
    }

    public interface GoogleSearchCallback{
        void searchDone(String url);
        void searchFailed();
    }
}

package com.tmaproject.mybakingbook.data.source.images;

import io.reactivex.Single;

/**
 * Created by tarekkma on 6/23/17.
 */

public interface ImageDataSource {
  Single<String> getImage(String recipeName);
}

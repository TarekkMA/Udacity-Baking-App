package com.tmaproject.mybakingbook.data.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract;
import org.parceler.Parcel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by tarekkma on 6/19/17.
 */

@Entity(tableName = DatabaseContract.STEP_TABLE_NAME,
    foreignKeys =
    @ForeignKey
        (entity = Recipe.class,
            parentColumns = "id",
            childColumns = "recipeId",
            onDelete = CASCADE))
@Parcel(Parcel.Serialization.BEAN)
public class Step {

  private int recipeId;

  @PrimaryKey
  @SerializedName("id")
  private Integer id;

  @SerializedName("shortDescription")
  private String shortDescription;

  @SerializedName("description")
  private String description;

  @SerializedName("videoURL")
  private String videoURL;

  @SerializedName("thumbnailURL")
  private String thumbnailURL;

  public int getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getVideoURL() {
    return videoURL;
  }

  public void setVideoURL(String videoURL) {
    this.videoURL = videoURL;
  }

  public String getThumbnailURL() {
    return thumbnailURL;
  }

  public void setThumbnailURL(String thumbnailURL) {
    this.thumbnailURL = thumbnailURL;
  }
}

package com.tmaproject.mybakingbook.data.pojo;

import android.arch.persistence.room.ColumnInfo;
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

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  //This is the database id
  private int _id;

  @SerializedName("id")
  //The webservice so-called "id"
  private Integer index;

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

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
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

  public int getId() {
    return _id;
  }

  public void setId(int id) {
    this._id = id;
  }
}

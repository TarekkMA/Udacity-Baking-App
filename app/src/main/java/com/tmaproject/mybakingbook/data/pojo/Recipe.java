package com.tmaproject.mybakingbook.data.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tmaproject.mybakingbook.data.source.recipes.local.DatabaseContract;
import java.util.List;
import org.parceler.Parcel;

/**
 * Created by tarekkma on 6/19/17.
 */

@Entity(tableName = DatabaseContract.RECIPES_TABLE_NAME)
@Parcel(Parcel.Serialization.BEAN)
public class Recipe {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("ingredients")
    @Expose @Ignore
    private List<Ingredient> ingredients = null;

    @SerializedName("steps")
    @Expose @Ignore
    private List<Step> steps = null;


    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

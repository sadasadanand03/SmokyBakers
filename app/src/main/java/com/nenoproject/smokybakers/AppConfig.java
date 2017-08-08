package com.nenoproject.smokybakers;

import com.nenoproject.smokybakers.pojo.RecipeDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sadanandk on 8/7/2017.
 */

public interface AppConfig {
    @GET("baking.json")
    Call<List<RecipeDetails>> getRecipeDetails();
}

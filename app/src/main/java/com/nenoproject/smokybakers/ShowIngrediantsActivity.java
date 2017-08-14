package com.nenoproject.smokybakers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.nenoproject.smokybakers.pojo.Ingredient;
import com.nenoproject.smokybakers.pojo.IngredientPojo;
import com.nenoproject.smokybakers.pojo.RecipeDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowIngrediantsActivity extends AppCompatActivity {
    String name;
    int position;
    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    RecyclerView rvShowIngrediants;
    ArrayList<IngredientPojo>  arrayListIngrediants;
    ImageView iv_show;
   public static int images[]={R.drawable.nutella_pie,R.drawable.brownies1,R.drawable.yellow_cake,R.drawable.cheesecake};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_ingrediants);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvShowIngrediants = (RecyclerView) findViewById(R.id.rvShowIngrediants);
        iv_show= (ImageView) findViewById(R.id.iv_show);
        arrayListIngrediants = new ArrayList<>();


        Intent i = getIntent();
        Bundle b = i.getExtras();
        name  = b.getString("foodItem");
        position = b.getInt("position");
        // Toast.makeText(this, "position is "+position+"food item is "+name, Toast.LENGTH_SHORT).show();
        setTitle(name);

        iv_show.setImageResource(images[position]);



        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(AppConfig.class).getRecipeDetails().enqueue(new Callback<List<RecipeDetails>>() {
            @Override
            public void onResponse(Call<List<RecipeDetails>> call, Response<List<RecipeDetails>> response) {

                List<Ingredient> recipeIngredient = response.body().get(position).getIngredients();
                for(int j =0 ;j<recipeIngredient.size();j++)
                {
                    float quantity = recipeIngredient.get(j).getQuantity();
                    String measure  = recipeIngredient.get(j).getMeasure();
                    String ingredient = recipeIngredient.get(j).getIngredient();

                    IngredientPojo ip = new IngredientPojo( measure,ingredient,quantity);
                    arrayListIngrediants.add(ip);
                   // Toast.makeText(ShowIngrediantsActivity.this, "  Quantity  => "+quantity+"   Measure  =>"+measure+"     Ingredient  =>"+ingredient, Toast.LENGTH_SHORT).show();
                }
                RecyclerAdapterForIngrediants aa = new RecyclerAdapterForIngrediants(ShowIngrediantsActivity.this,R.layout.carditem_ingrediants,arrayListIngrediants);
                RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(ShowIngrediantsActivity.this);
                rvShowIngrediants.setLayoutManager(mlayoutManager);
                rvShowIngrediants.setAdapter(aa);
            }
            @Override
            public void onFailure(Call<List<RecipeDetails>> call, Throwable t) {
                Log.e(" on failure",t.getMessage());
                Toast.makeText(ShowIngrediantsActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}

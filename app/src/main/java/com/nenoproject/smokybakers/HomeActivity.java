package com.nenoproject.smokybakers;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nenoproject.smokybakers.pojo.Ingredient;
import com.nenoproject.smokybakers.pojo.RecipeDetails;
import com.nenoproject.smokybakers.pojo.Step;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    RecyclerView lvFoodItem;
    ArrayList<String> arrayListFoodItem;
    ArrayList<Integer> image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lvFoodItem = (RecyclerView) findViewById(R.id.lvFoodItem);

        arrayListFoodItem = new ArrayList<>();
        image = new ArrayList<>();
        image.add(R.drawable.nutella_pie);
        image.add(R.drawable.brownies);
        image.add(R.drawable.yellow_cake);
        image.add(R.drawable.cheesecake);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            retrofit.create(AppConfig.class).getRecipeDetails().enqueue(new Callback<List<RecipeDetails>>() {
                @Override
                public void onResponse(Call<List<RecipeDetails>> call, Response<List<RecipeDetails>> response) {

                    for(int i =0;i<response.body().size();i++)
                    {
                        String name =  response.body().get(i).getName();
                        arrayListFoodItem.add(name);
                      /*  List<Ingredient> recipeIngredient = response.body().get(i).getIngredients();
                        for(int j =0 ;j<recipeIngredient.size();j++)
                        {
                           Float quantity = recipeIngredient.get(j).getQuantity();
                        }
*/
                    }
                    RecyclerAdapter aa = new RecyclerAdapter(HomeActivity.this,R.layout.card_item,arrayListFoodItem,image);
                    RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(HomeActivity.this);
                    lvFoodItem.setLayoutManager(mlayoutManager);
                    lvFoodItem.setAdapter(aa);
                }
                @Override
                public void onFailure(Call<List<RecipeDetails>> call, Throwable t) {
                    Log.e(" on failure",t.getMessage());
                    Toast.makeText(HomeActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
                }
            });
    }
}

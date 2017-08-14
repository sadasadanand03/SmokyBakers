package com.nenoproject.smokybakers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nenoproject.smokybakers.pojo.Ingredient;
import com.nenoproject.smokybakers.pojo.IngredientPojo;
import com.nenoproject.smokybakers.pojo.RecipeDetails;
import com.nenoproject.smokybakers.pojo.Step;
import com.nenoproject.smokybakers.pojo.StepsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeActivity extends AppCompatActivity {
    RecyclerView rvRecipeActivity;
    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    int position;
    Button btnShowIngrediant;
    String name;
    public static ArrayList<StepsPojo> arrayListSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        rvRecipeActivity  = (RecyclerView) findViewById(R.id.rvRecipeActivity);
       btnShowIngrediant = (Button) findViewById(R.id.btnGetIngradiants);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayListSteps = new ArrayList<>();

        Intent i = getIntent();
        Bundle b = i.getExtras();
        name  = b.getString("foodItem");
        position = b.getInt("position");
        setTitle(name);

        btnShowIngrediant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecipeActivity.this,ShowIngrediantsActivity.class);
                i.putExtra("foodItem",name);
                i.putExtra("position",position);
                startActivity(i);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(AppConfig.class).getRecipeDetails().enqueue(new Callback<List<RecipeDetails>>() {
            @Override
            public void onResponse(Call<List<RecipeDetails>> call, Response<List<RecipeDetails>> response) {

                List<Step> recipeSteps = response.body().get(position).getSteps();
                for(int j =0 ;j<recipeSteps.size();j++)
                {
                    String shortDescription = recipeSteps.get(j).getShortDescription();
                    String description  = recipeSteps.get(j).getDescription();
                    String videoURL = recipeSteps.get(j).getVideoURL();
                    StepsPojo sp = new StepsPojo( shortDescription,description,videoURL);
                    arrayListSteps.add(sp);
                }

                //TODO (add recycle view here to show r)
              //  Toast.makeText(RecipeActivity.this, ""+arrayListSteps.get(1).getShortDescription(), Toast.LENGTH_SHORT).show();
                RecyclerAdapterForSteps aa = new RecyclerAdapterForSteps(RecipeActivity.this,R.layout.carditem_steps,arrayListSteps);
                RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(RecipeActivity.this);
                rvRecipeActivity.setLayoutManager(mlayoutManager);
                rvRecipeActivity.setAdapter(aa);
                rvRecipeActivity.addOnItemTouchListener(new OnRecyclerItemClickListener(RecipeActivity.this, new OnRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Auto-generated method stub

                       // Toast.makeText(RecipeActivity.this, ""+arrayListSteps.get(position).getVideoURL(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RecipeActivity.this, PlayVideoActivity.class);
                            i.putExtra("position", position);
                            Log.e("", "" + arrayListSteps.get(position).getVideoURL());
                            startActivity(i);


                    }
                }));
               // Toast.makeText(RecipeActivity.this, "sada", Toast.LENGTH_SHORT).show();


            }
            @Override
            public void onFailure(Call<List<RecipeDetails>> call, Throwable t) {
                Log.e(" on failure",t.getMessage());
                Toast.makeText(RecipeActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}

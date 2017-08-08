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
import com.nenoproject.smokybakers.pojo.RecipeDetails;
import com.nenoproject.smokybakers.pojo.Step;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        rvRecipeActivity  = (RecyclerView) findViewById(R.id.rvRecipeActivity);
        btnShowIngrediant = (Button) findViewById(R.id.btnGetIngradiants);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        name  = b.getString("foodItem");
        position = b.getInt("position");
       // Toast.makeText(this, "position is "+position+"food item is "+name, Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}

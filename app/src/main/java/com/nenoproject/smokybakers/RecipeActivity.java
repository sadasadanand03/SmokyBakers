package com.nenoproject.smokybakers;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.devbrackets.android.exomedia.ui.widget.VideoView;


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
    private RecyclerView rvRecipeActivity;
    private int position;
    private String name;
    public static ArrayList<StepsPojo> arrayListSteps;
    private VideoView vv;
    private TextView tvDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        rvRecipeActivity  = (RecyclerView) findViewById(R.id.rvRecipeActivity);
        vv = (VideoView) findViewById(R.id.video_view);
        tvDes = (TextView) findViewById(R.id.tvDescribe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayListSteps = new ArrayList<>();

        Intent i = getIntent();
        Bundle b = i.getExtras();
        name  = b.getString("foodItem");
        position = b.getInt("position");
        setTitle(name);

        String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
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

                /*
                  this is to play first video when we open the activity
                 */
                if(isTablet(RecipeActivity.this)) {
                    vv.setVisibility(View.VISIBLE);
                    vv.setVideoURI(Uri.parse(arrayListSteps.get(0).getVideoURL()));
                    tvDes.setText(arrayListSteps.get(0).getDescription());
                }
                rvRecipeActivity.addOnItemTouchListener(new OnRecyclerItemClickListener(RecipeActivity.this, new OnRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Auto-generated method stub
                            if(isTablet(RecipeActivity.this))
                            {
                                if(arrayListSteps.get(position).getVideoURL().equals(""))
                                {
                                    vv.setVisibility(View.GONE);
                                    Toast.makeText(RecipeActivity.this, "No video available", Toast.LENGTH_SHORT).show();
                                    tvDes.setText(arrayListSteps.get(position).getDescription());

                                }
                                else {
                                    vv.setVisibility(View.VISIBLE);
                                    vv.setVideoURI(Uri.parse(arrayListSteps.get(position).getVideoURL()));
                                    tvDes.setText(arrayListSteps.get(position).getDescription());
                                }

                            }else
                            {
                                // Toast.makeText(RecipeActivity.this, ""+arrayListSteps.get(position).getVideoURL(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RecipeActivity.this, PlayVideoActivity.class);
                                i.putExtra("position", position);
                                Log.e("", "" + arrayListSteps.get(position).getVideoURL());
                                startActivity(i);
                            }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_ingredients:
                Intent i = new Intent(RecipeActivity.this, ShowIngrediantsActivity.class);
                i.putExtra("foodItem", name);
                i.putExtra("position", position);
                startActivity(i);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * this method to get it is tablet or mobile.
     */
    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}

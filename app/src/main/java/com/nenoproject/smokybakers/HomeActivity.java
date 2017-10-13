package com.nenoproject.smokybakers;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nenoproject.smokybakers.pojo.RecipeDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity
{

    private RecyclerView lvFoodItem;
    private ArrayList<String> arrayListFoodItem;
    private ArrayList<Integer> image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lvFoodItem = (RecyclerView) findViewById(R.id.lvFoodItem);

        arrayListFoodItem = new ArrayList<>();
        image = new ArrayList<>();
        image.add(R.drawable.nutella_pie);
        image.add(R.drawable.brownies1);
        image.add(R.drawable.yellow_cake);
        image.add(R.drawable.cheesecake);

        String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            retrofit.create(AppConfig.class).getRecipeDetails().enqueue(new Callback<List<RecipeDetails>>() {
                @Override
                public void onResponse(Call<List<RecipeDetails>> call, Response<List<RecipeDetails>> response) {

                    for(int i =0;i<response.body().size();i++)
                    {
                        String name =  response.body().get(i).getName();
                        arrayListFoodItem.add(name);
                    }
                    if(isTablet(HomeActivity.this))
                    {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        RecyclerAdapter rd = new RecyclerAdapter(HomeActivity.this, arrayListFoodItem,image);
                        RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(HomeActivity.this,2);


                        lvFoodItem.setLayoutManager(mlayoutManager);
                        lvFoodItem.setAdapter(rd);
                    }
                    else
                    {
                        RecyclerAdapter aa = new RecyclerAdapter(HomeActivity.this, arrayListFoodItem,image);
                        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(HomeActivity.this);
                        lvFoodItem.setLayoutManager(mlayoutManager);
                        lvFoodItem.setAdapter(aa);
                    }
                }
                @Override
                public void onFailure(Call<List<RecipeDetails>> call, Throwable t) {
                    Log.e(" on failure",t.getMessage());
                    Toast.makeText(HomeActivity.this, "On Failure", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    // this is demo to set default value at zero position
/*
    public ArrayList<DeliveryTypeDetails> getDelivetTypeDtl() {
        Cursor c = null;
        ArrayList<DeliveryTypeDetails> b = new ArrayList<DeliveryTypeDetails>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "select DELIVERY_POINT_CODE,DELIVERY_POINT_DESC from CN_DELIVERY_POINT ";
            c = db.rawQuery(query, null);

            if (c.getCount() > 0) {
                DeliveryTypeDetails dtd = new DeliveryTypeDetails();
                dtd.setName("Select");
                dtd.setCode("0");
                b.add(dtd);
                if (c.moveToFirst())
                    do {

                        DeliveryTypeDetails iD = new DeliveryTypeDetails();

                        iD.setCode(c.getString(0));
                        iD.setName(c.getString(1));

                        b.add(iD);
                    } while (c.moveToNext());


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (c != null) {
                c.close();
            }

        }

        return b;
    }
*/
}

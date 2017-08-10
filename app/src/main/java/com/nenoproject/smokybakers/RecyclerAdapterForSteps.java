package com.nenoproject.smokybakers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nenoproject.smokybakers.pojo.IngredientPojo;
import com.nenoproject.smokybakers.pojo.StepsPojo;

import java.util.ArrayList;

/**
 * Created by Sadanandk on 8/10/2017.
 */

public class RecyclerAdapterForSteps extends RecyclerView.Adapter<RecyclerAdapterForSteps.MyViewHolder> {

    Context context;
    int carditem_ingrediants;
    ArrayList<StepsPojo> arrayListIngredients;
    public RecyclerAdapterForSteps(Context context, int carditem_ingrediants, ArrayList<StepsPojo> arrayListIngredients) {
        this.context = context;
        this.carditem_ingrediants = carditem_ingrediants;
        this.arrayListIngredients = arrayListIngredients;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvQuantity;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvrecipeList);

        }
    }
    @Override
    public RecyclerAdapterForSteps.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(carditem_ingrediants , parent , false);
        return new RecyclerAdapterForSteps.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterForSteps.MyViewHolder holder, final int position) {
        holder.tvQuantity.setText("" + arrayListIngredients.get(position).getShortDescription());
    }



    @Override
    public int getItemCount() {
        return arrayListIngredients.size();
    }

}

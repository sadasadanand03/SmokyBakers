package com.nenoproject.smokybakers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nenoproject.smokybakers.pojo.IngredientPojo;

import java.util.ArrayList;

/**
 * Created by sadanandk on 8/8/2017.
 */

@SuppressWarnings("ALL")
public class RecyclerAdapterForIngrediants extends RecyclerView.Adapter<RecyclerAdapterForIngrediants.MyViewHolder> {

    private final int carditem_ingrediants;
    private final ArrayList<IngredientPojo> arrayListIngredients;
    public RecyclerAdapterForIngrediants(Context context, ArrayList<IngredientPojo> arrayListIngredients) {
        Context context1 = context;
        this.carditem_ingrediants = R.layout.carditem_ingrediants;
       this.arrayListIngredients = arrayListIngredients;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder
    {
        final TextView tvQuantity;
        final TextView tvMeasure;
        final TextView tvIngredient;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            tvMeasure = (TextView) itemView.findViewById(R.id.tvMeasure);
            tvIngredient = (TextView) itemView.findViewById(R.id.tvIngredient);

        }
    }
    @Override
    public RecyclerAdapterForIngrediants.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(carditem_ingrediants , parent , false);
        return new RecyclerAdapterForIngrediants.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterForIngrediants.MyViewHolder holder, final int position) {
        holder.tvQuantity.setText(""+arrayListIngredients.get(position).getQuantity()+" ");
        holder.tvMeasure.setText(arrayListIngredients.get(position).getMeasure());
        holder.tvIngredient.setText(arrayListIngredients.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        return arrayListIngredients.size();
    }

}

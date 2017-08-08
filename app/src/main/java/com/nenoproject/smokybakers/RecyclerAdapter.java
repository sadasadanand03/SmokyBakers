package com.nenoproject.smokybakers;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;

/**
 * Created by sadanandk on 8/8/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    int card_item;
    ArrayList<String> arrayListFoodItem;
    ArrayList<Integer> image;
    public RecyclerAdapter(Context context, int card_item, ArrayList<String> arrayListFoodItem,ArrayList<Integer> image) {
        this.context = context;
        this.card_item = card_item;
        this.arrayListFoodItem = arrayListFoodItem;
        this.image = image;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.ivFoodImage);
            tv = (TextView) itemView.findViewById(R.id.tvFoodItem);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(card_item , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(arrayListFoodItem.get(position));
        Glide.with(context)
                .load(image.get(position))
                .asBitmap()
                .into(new BitmapImageViewTarget(holder.iv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        //Play with bitmap
                        super.setResource(resource);
                    }
                });

    }

    @Override
    public int getItemCount() {
       return arrayListFoodItem.size();
    }
}

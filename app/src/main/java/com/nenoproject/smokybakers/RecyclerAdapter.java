package com.nenoproject.smokybakers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;

/**
 * Created by sadanandk on 8/8/2017.
 */

@SuppressWarnings("ALL")
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private final Context context;
    private final int card_item;
    private final ArrayList<String> arrayListFoodItem;
    private final ArrayList<Integer> image;
    public RecyclerAdapter(Context context, ArrayList<String> arrayListFoodItem, ArrayList<Integer> image) {
        this.context = context;
        this.card_item = R.layout.card_item;
        this.arrayListFoodItem = arrayListFoodItem;
        this.image = image;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder
    {
        final ImageView iv;
        final TextView tv;
        final LinearLayout ll;
        public MyViewHolder(View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.ivFoodImage);
            tv = (TextView) itemView.findViewById(R.id.tvFoodItem);
            ll = (LinearLayout) itemView.findViewById(R.id.llView);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(card_item , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(arrayListFoodItem.get(position));
        Glide.with(context)
                .load(image.get(position))
                .asBitmap()
                .into(new BitmapImageViewTarget(holder.iv) {
                });
        /*
          this is alternet option to listen the action from recycler view.
          simple get the reference of an item and set the setOnClickListener on that item reference
         */
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "position is "+position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context,RecipeActivity.class);
                i.putExtra("foodItem",arrayListFoodItem.get(position));
                i.putExtra("position",position);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
       return arrayListFoodItem.size();
    }
}

package com.nenoproject.smokybakers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by maheshs on 8/10/2017.
 */

@SuppressWarnings("ALL")
class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private final GestureDetector gestureDetector;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    public OnRecyclerItemClickListener(Context context, OnItemClickListener listener) {
        this.listener = listener;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if(childView != null && listener != null && gestureDetector.onTouchEvent(e)) {
            listener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean arg0) {
        // TODO Auto-generated method stub
    }
}
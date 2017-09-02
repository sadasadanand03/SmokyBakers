package com.nenoproject.smokybakers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.nenoproject.smokybakers.pojo.StepsPojo;

import java.util.ArrayList;

public class PlayVideoActivity extends AppCompatActivity implements OnPreparedListener{
    private VideoView videoView;
    private TextView tvDescribe;
    private ImageView next;
    private ImageView prev;
     private ArrayList<StepsPojo> arrayListSteps;
     private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        arrayListSteps  = RecipeActivity.arrayListSteps;
        videoView = (VideoView)findViewById(R.id.video_view);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        tvDescribe = (TextView) findViewById(R.id.tvDescribe);

        videoView.setOnPreparedListener(this);

        //For checking that screen orientation.
        if(savedInstanceState==null)
        {
            Intent i = getIntent();
            Bundle b = i.getExtras();
            position = b.getInt("position");
            if(position==0)
            {
                prev.setVisibility(View.GONE);
            }
            if (arrayListSteps.get(position).getVideoURL().equals("")){
                videoView.setVisibility(View.GONE);
                Toast.makeText(this, "No video available.", Toast.LENGTH_SHORT).show();

            }
            videoView.setVideoURI(Uri.parse(arrayListSteps.get(position).getVideoURL()));
            tvDescribe.setText(arrayListSteps.get(position).getDescription());
        }
        else
        {
            position = savedInstanceState.getInt("position");
            if(position==0)
            {
                prev.setVisibility(View.GONE);
            }
            if (arrayListSteps.get(position).getVideoURL().equals("")){
                videoView.setVisibility(View.GONE);
                Toast.makeText(this, "No video available.", Toast.LENGTH_SHORT).show();

            }
            videoView.setVideoURI(Uri.parse(arrayListSteps.get(position).getVideoURL()));
            tvDescribe.setText(arrayListSteps.get(position).getDescription());
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position!=0)
                {
                    prev.setVisibility(View.VISIBLE);
                }
                else {
                    prev.setVisibility(View.GONE);
                }
                if (position==arrayListSteps.size()) {

                    next.setVisibility(View.GONE);

                } else {
                    if (arrayListSteps.get(position).getVideoURL().equals("")){
                        videoView.setVisibility(View.GONE);
                        Toast.makeText(PlayVideoActivity.this, "No video available.", Toast.LENGTH_SHORT).show();
                        tvDescribe.setText(arrayListSteps.get(position).getDescription());

                    }
                    else {
                        videoView.setVisibility(View.VISIBLE);
                        videoView.setVideoURI(Uri.parse(arrayListSteps.get(position).getVideoURL()));
                        tvDescribe.setText(arrayListSteps.get(position).getDescription());
                    }
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                position--;
                if(position==0)
                {
                    prev.setVisibility(View.GONE);
                }
                else {
                    prev.setVisibility(View.VISIBLE);
                }
                if(position==arrayListSteps.size())
                {
                    next.setVisibility(View.GONE);
                }
                else
                {
                    if (arrayListSteps.get(position).getVideoURL().equals("")){
                        videoView.setVisibility(View.GONE);
                        Toast.makeText(PlayVideoActivity.this, "No video available.", Toast.LENGTH_SHORT).show();
                        tvDescribe.setText(arrayListSteps.get(position).getDescription());
                        next.setVisibility(View.VISIBLE);

                    }
                    else {
                        videoView.setVisibility(View.VISIBLE);
                        videoView.setVideoURI(Uri.parse(arrayListSteps.get(position).getVideoURL()));
                        tvDescribe.setText(arrayListSteps.get(position).getDescription());
                        next.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }
    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        videoView.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("current_position",videoView.getCurrentPosition());
        outState.putInt("position",position);
    }



    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        videoView.seekTo(savedInstanceState.getLong("current_position"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}

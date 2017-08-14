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
    private String url;
    private VideoView videoView;
    TextView tvDescribe;
    ImageView next,prev;
     ArrayList<StepsPojo> arrayListSteps;
     int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        position = b.getInt("position");

        arrayListSteps  = RecipeActivity.arrayListSteps;
        videoView = (VideoView)findViewById(R.id.video_view);
        next = (ImageView) findViewById(R.id.next);
        prev = (ImageView) findViewById(R.id.prev);
        tvDescribe = (TextView) findViewById(R.id.tvDescribe);
        if(position==0)
        {
            prev.setVisibility(View.GONE);
        }
        if (arrayListSteps.get(position).getVideoURL().equals("")){
            videoView.setVisibility(View.GONE);
            Toast.makeText(this, "No video available.", Toast.LENGTH_SHORT).show();

        }


        videoView.setOnPreparedListener(this);

        //For now we just picked an arbitrary item to play
        videoView.setVideoURI(Uri.parse(arrayListSteps.get(position).getVideoURL()));
        tvDescribe.setText(arrayListSteps.get(position).getDescription());
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
                   // Toast.makeText(PlayVideoActivity.this, "This last video", Toast.LENGTH_SHORT).show();



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
}

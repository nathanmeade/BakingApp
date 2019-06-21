package com.example.android.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

public class RecipeStepDetailsActivity extends AppCompatActivity {
    private Bundle stepsBundle;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);
        Intent intent = getIntent();
/*        stepsBundle = intent.getBundleExtra("steps");
        ArrayList<Step> steps = (ArrayList<Step>) stepsBundle.getSerializable("steps");
        TextView textView = findViewById(R.id.steps_text_view);
        textView.setText("test");
        for (Step step: steps) {
            textView.append(step.getShortDescription());
        }*/
        textView = findViewById(R.id.steps_text_view);
        int id = intent.getIntExtra("id", 0);
        String shortDescription = intent.getStringExtra("shortDescription");
        String description = intent.getStringExtra("description");
        String videoUrl = intent.getStringExtra("videoUrl");
        String thumbnailUrl = intent.getStringExtra("thumbnailUrl");
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());
        PlayerView playerView = findViewById(R.id.player_view);
        playerView.setPlayer(player);
        if (shortDescription.equals("Recipe Introduction")){
            //textView.setText(shortDescription + "\n\n");
            textView.setText("");
        }
        else {
            //textView.setText("Step " + id + ": " + shortDescription + "\n\n");
            textView.setText("Step ");
        }
        textView.append(description);
        //textView.append(thumbnailUrl);
        //textView.append(toString(id));
        //textView.append(videoUrl);
        Log.d("nathanTest", thumbnailUrl);
        //String url = steps.get(0).getVideoURL();
        //Uri uri = Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
        player.prepare(mediaSource, true, false);
        player.setPlayWhenReady(true);
    }
}

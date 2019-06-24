package com.example.android.bakingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Bundle recipeBundle;
    private TextView textView;
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private Button previousButton;
    private Button nextButton;
    private Intent intent;
    private Boolean previousButtonBoolean;
    private Boolean nextButtonBoolean;
    private int id;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);
        Intent intent = getIntent();
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
/*        stepsBundle = intent.getBundleExtra("steps");
        ArrayList<Step> steps = (ArrayList<Step>) stepsBundle.getSerializable("steps");
        TextView textView = findViewById(R.id.steps_text_view);
        textView.setText("test");
        for (Step step: steps) {
            textView.append(step.getShortDescription());
        }*/
        textView = findViewById(R.id.steps_text_view);
        id = intent.getIntExtra("id", 0);
        stepsBundle = intent.getBundleExtra("steps");
        recipeBundle = intent.getBundleExtra("recipe");

        ArrayList<Step> steps = (ArrayList<Step>) stepsBundle.getSerializable("steps");
        //ArrayList<Integer> mIds = new ArrayList<>();
        ArrayList<String> mShortDescriptions = new ArrayList<>();
        size = steps.size();
        ArrayList<String> mDescriptions = new ArrayList<>();
        ArrayList<String> mVideoUrls = new ArrayList<>();
        //ArrayList<String> mThumbnailUrls = new ArrayList<>();
        for (Step step : steps ){
            //mIds.add(step.getId());
            mShortDescriptions.add(step.getShortDescription());
            mDescriptions.add(step.getDescription());
            mVideoUrls.add(step.getVideoURL());
            //mThumbnailUrls.add(step.getThumbnailURL());
        }

        String shortDescription = mShortDescriptions.get(id);
        String description = mDescriptions.get(id);
        String videoUrl = mVideoUrls.get(id);
        //String thumbnailUrl = intent.getStringExtra("thumbnailUrl");
        //SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(

        if (shortDescription.equals("Recipe Introduction")){
            //textView.setText(shortDescription + "\n\n");
            textView.setText("");
        }
        else {
            //textView.setText("Step " + id + ": " + shortDescription + "\n\n");
            textView.setText("Step ");
        }
        //textView.append();
        textView.append(description);
        //textView.append(thumbnailUrl);
        //textView.append(toString(id));
        //textView.append(videoUrl);
        //Log.d("nathanTest", thumbnailUrl);
        //String url = steps.get(0).getVideoURL();
        //Uri uri = Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");

        playerView = findViewById(R.id.player_view);

        createVideoPlayer(videoUrl);
        nextAndPreviousSteps();
    }

    private void createVideoPlayer(String url){
        if (url.equals("")){
            //playerView.setVisibility(View.INVISIBLE);
/*            Bitmap bitmap = new Bitmap()
            playerView.setDefaultArtwork(R.drawable.ic_launcher_background);
            playerView.setUseArtwork(true);*/
            playerView.setVisibility(View.GONE);
        }
        else {
            SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(this),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(player);
            Uri uri = Uri.parse(url);
            MediaSource mediaSource = new ExtractorMediaSource.Factory(
                    new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                    createMediaSource(uri);
            player.prepare(mediaSource, true, false);
            player.setPlayWhenReady(true);
        }
    }

    private void nextAndPreviousSteps(){
        //if ((id > 0)&& id<(size-1)) {
        if ((id > 0)&& id<(size - 1)) {
            //previous step true
            previousButtonBoolean = true;
            //next step true
            nextButtonBoolean = true;
        }
        else if (id > 0){
            //previous step true
            nextButton.setVisibility(View.GONE);
            nextButtonBoolean = false;
            previousButtonBoolean = true;
        }
        else {
            //next step true
            previousButton.setVisibility(View.GONE);
            previousButtonBoolean = false;
            nextButtonBoolean = true;
        }

    }

    public void previousButton(View view){
        if (previousButtonBoolean) {
            stepIntent((id-1));
        }
    }

    public void nextButton(View view){
        if (nextButtonBoolean) {
            stepIntent((id+1));
        }
    }

    public void backArrow(View view){
        Intent intent = new Intent(this, RecipeStepsActivity.class);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }

    //private void setPreviousButton(Boolean exists, )

    private void stepIntent(int step){
        intent = new Intent(this, RecipeStepDetailsActivity.class);
        intent.putExtra("id", step);
        intent.putExtra("steps", stepsBundle);
        startActivity(intent);
    }
}

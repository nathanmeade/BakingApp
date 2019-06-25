package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class StepDetailsFragment extends Fragment {

    private Bundle stepsBundle;
    private Bundle recipeBundle;
    private Bundle idBundle;
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

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
/*            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_details, container, false);
        //textView = findViewById(R.id.steps_text_view);
        textView = rootView.findViewById(R.id.steps_text_view);
        playerView = rootView.findViewById(R.id.player_view);
        //id = intent.getIntExtra("id", 0);

        //Need to get this working:
        //id = 0;
        //id = getArguments().getInt("id", 0);
        id = idBundle.getInt("id");

        //stepsBundle = intent.getBundleExtra("steps");
/*        stepsBundle = getArguments().getBundle("steps");
        //recipeBundle = intent.getBundleExtra("recipe");
        recipeBundle = getArguments().getBundle("recipe");*/

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



        createVideoPlayer(videoUrl);
        // Inflate the layout for this fragment
        return rootView;
    }

/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                    new DefaultRenderersFactory(getContext()),
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

    public void setIdBundle(Bundle bundle){
        idBundle = bundle;
    }

    public void setStepsBundle(Bundle bundle){
        stepsBundle = bundle;
    }

    public void setRecipeBundle(Bundle bundle){
        recipeBundle = bundle;
    }
}

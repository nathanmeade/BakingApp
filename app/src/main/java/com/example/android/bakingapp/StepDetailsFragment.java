package com.example.android.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private Bundle recipeBundle;
    private Bundle idBundle;
    private TextView textView;
    private PlayerView playerView;
    private int id;

    public StepDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_details, container, false);
        textView = rootView.findViewById(R.id.steps_text_view);
        playerView = rootView.findViewById(R.id.player_view);
        id = idBundle.getInt("id");
        Recipe recipe = (Recipe) recipeBundle.getSerializable("recipe");
        ArrayList<Step> steps = recipe.getSteps();
        Step step = steps.get(id);
        String shortDescription = step.getShortDescription();
        String description = step.getDescription();
        String videoUrl = step.getVideoURL();
        if (shortDescription.equals("Recipe Introduction")){
            textView.setText("");
        }
        else {
            textView.setText("Step ");
        }
        textView.append(description);
        createVideoPlayer(videoUrl);
        return rootView;
    }

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

    public void setRecipeBundle(Bundle bundle){
        recipeBundle = bundle;
    }
}

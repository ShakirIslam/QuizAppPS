package com.student.shakirislam.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerCustomActivity extends YouTubeBaseActivity {
    private static final String TAG = "YoutubePlayerCustom";

    private Button returnButton;
    private YouTubePlayerView mYoutubePlayerView;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;
    public static String youtubeVidID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player_custom);

        returnButton = (Button) findViewById(R.id.button_back);
        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.view_youtube);



        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: Player has been successfuly initialized");
                //Gets video ID attached to question
                youTubePlayer.loadVideo(youtubeVidID);
                //Test Video
                //youTubePlayer.loadVideo("W4hTJybfU7s");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        playVideo();



        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Return back to the quiz
                onBackPressed();
            }
        });

    }

    private void playVideo() {
        //Initializes the Youtube Player
        Log.d(TAG, "playVideo: Initilizing YT Player");
        mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);

    }


}

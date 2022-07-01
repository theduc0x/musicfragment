package com.example.appmusicfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PlaySongActivity extends AppCompatActivity {
    public static int posSong;
    public static int posPlay = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Intent getPosition = getIntent();
            int posSong = getPosition.getIntExtra(Util.EXTRA_POSITION, 0);

        FragmentMusicPlay fragmentMusicPlay
                = (FragmentMusicPlay) getSupportFragmentManager()
                .findFragmentById(R.id.fm_play_song_portrait);
        fragmentMusicPlay.onPause();
        fragmentMusicPlay.setPosition(posSong);
    }

}
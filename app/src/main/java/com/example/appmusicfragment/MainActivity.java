package com.example.appmusicfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.appmusicfragment.my_interface.TransPositionToMusicPlay;

public class MainActivity extends AppCompatActivity implements TransPositionToMusicPlay {
    public static int posPlaySong = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void TransPosition(int position) {
        FragmentMusicPlay fragmentMusicPlay
                = (FragmentMusicPlay) getSupportFragmentManager()
                .findFragmentById(R.id.fm_play_song);
////        C2
//        Configuration configuration = getResources().getConfiguration();
//        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (fragmentMusicPlay != null && fragmentMusicPlay.isInLayout()) {
            fragmentMusicPlay.setPosition(position);
        } else {
            Intent toPlaySong = new Intent(MainActivity.this, PlaySongActivity.class);
            toPlaySong.putExtra(Util.EXTRA_POSITION, position);
            startActivity(toPlaySong);
        }
    }
}
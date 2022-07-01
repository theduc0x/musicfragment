package com.example.appmusicfragment;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FragmentMusicPlay extends Fragment {
    private TextView tvName, tvStart, tvTimeTotal, tvSinger;
    private SeekBar sbTime;
    private ImageButton ibPrev, ibPlay, ibNext;
    private ImageView ivImage;
    public static int position = 0;
    Animation animation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_play, container, false);
        tvTimeTotal = view.findViewById(R.id.tv_time_end);
        tvSinger = view.findViewById(R.id.tv_singer);
        tvName = view.findViewById(R.id.tv_name);
        tvStart = view.findViewById(R.id.tv_time_start);
        sbTime = view.findViewById(R.id.sb_time_song);
        ibPrev = view.findViewById(R.id.ib_prev);
        ibPlay = view.findViewById(R.id.ib_play);
        ibNext = view.findViewById(R.id.ib_next);
        ivImage = view.findViewById(R.id.iv_image);

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_rotate);

        createMediaPlayer();


        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.mediaPlayer.isPlaying()) {
                    Util.mediaPlayer.pause();
                    ibPlay.setImageResource(R.drawable.ic_play);
                } else {
                    Util.mediaPlayer.start();
                    ibPlay.setImageResource(R.drawable.ic_pause);
                }
                setTimeTotal();
                updateTimeSong();
                ivImage.startAnimation(animation);
            }
        });

        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSong();
            }
        });

        ibPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if (position < 0 ) {
                    position = Util.listSong.size() - 1;
                }
                if (Util.mediaPlayer.isPlaying()) {
                    Util.mediaPlayer.stop();
                }
                createMediaPlayer();
                Util.mediaPlayer.start();
                ibPlay.setImageResource(R.drawable.ic_pause);
                setTimeTotal();
            }
        });

        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Util.mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Util.mediaPlayer.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Util.mediaPlayer.stop();
    }

    private void setTimeTotal() {
        SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
        tvTimeTotal.setText(dinhdangGio.format(Util.mediaPlayer.getDuration()));
        sbTime.setMax(Util.mediaPlayer.getDuration());
    }

    private void updateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
                tvStart.setText(dinhdangGio.format(Util.mediaPlayer.getCurrentPosition()));
                sbTime.setProgress(Util.mediaPlayer.getCurrentPosition());
                Util.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        nextSong();
                    }
                });
                handler.postDelayed(this, 500);
            }
        },100);
    }

    private void nextSong() {
        position++;
        if (position > Util.listSong.size() - 1) {
            position = 0;
        }
        if (Util.mediaPlayer.isPlaying()) {
            Util.mediaPlayer.stop();
        }
        createMediaPlayer();
        Util.mediaPlayer.start();
        ibPlay.setImageResource(R.drawable.ic_pause);
        setTimeTotal();
    }

    private void createMediaPlayer() {
        Util.mediaPlayer
                = MediaPlayer.create(getActivity(),
                Util.listSong.get(position).getFile());
        tvName.setText(Util.listSong.get(position).getName());
        ivImage.setImageResource(Util.listSong.get(position).getImage());
        tvSinger.setText(Util.listSong.get(position).getSinger());

    }
    // set lại song khi click bên ngoài
    public void setPosition(int vitri) {
        if (Util.mediaPlayer.isPlaying()) {
            Util.mediaPlayer.stop();
        }
        ivImage.startAnimation(animation);
        position = vitri;
        createMediaPlayer();
        Util.mediaPlayer.start();
        ibPlay.setImageResource(R.drawable.ic_pause);
        updateTimeSong();
        setTimeTotal();
    }

}
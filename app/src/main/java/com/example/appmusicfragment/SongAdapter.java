package com.example.appmusicfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicfragment.my_interface.IClickItemSongListener;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private ArrayList<Song> listSong;
    private IClickItemSongListener iClickItemSongListener;

    public SongAdapter(ArrayList<Song> listSong, IClickItemSongListener iClickItemSongListener) {
        this.listSong = listSong;
        this.iClickItemSongListener = iClickItemSongListener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = listSong.get(position);
        if (song == null) {
            return ;
        }

        holder.tvName.setText(song.getName());
        holder.ivImage.setImageResource(song.getImage());
        holder.tvSinger.setText(song.getSinger());

        holder.llItemSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iClickItemSongListener != null) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        iClickItemSongListener.onClickItemSong(pos);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listSong != null) {
            return listSong.size();
        }
        return 0;
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvName, tvSinger;
        private LinearLayout llItemSong;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image_song);
            tvName = itemView.findViewById(R.id.tv_name_song);
            tvSinger = itemView.findViewById(R.id.tv_singer_song);
            llItemSong = itemView.findViewById(R.id.ll_item_song);
        }
    }
}

package com.example.appmusicfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appmusicfragment.my_interface.IClickItemSongListener;
import com.example.appmusicfragment.my_interface.TransPositionToMusicPlay;

import java.util.ArrayList;

public class FragmentMusicName extends Fragment {
    RecyclerView rvSong;
    SongAdapter adapter;
    TransPositionToMusicPlay transPositionToMusicPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_name, container, false);

        transPositionToMusicPlay = (TransPositionToMusicPlay) getActivity();
        rvSong = view.findViewById(R.id.rv_song);
        anhXa();
        adapter = new SongAdapter(Util.listSong, new IClickItemSongListener() {
            @Override
            public void onClickItemSong(int position) {
                transPositionToMusicPlay.TransPosition(position);
            }
        });

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        RecyclerView.ItemDecoration decoration
                = new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL);

        rvSong.setLayoutManager(linearLayoutManager);
        rvSong.addItemDecoration(decoration);
        rvSong.setAdapter(adapter);

        return view;
    }

    private void anhXa() {
        Util.listSong = new ArrayList<>();
        Util.listSong.add(new Song("Chạy về nơi phía anh",
                "Khắc Việt",
                R.drawable.chayvenoiphiaanh_khacviet,
                R.raw.chayvenoiphiaanh_khacviet));

        Util.listSong.add(new Song("Đế vương",
                "Đình Dũng",
                R.drawable.devuong_dinhdung,
                R.raw.devuong_dinhdung));

        Util.listSong.add(new Song("Yêu đương khó quá thì chạy về khóc với anh",
                "Erik",
                R.drawable.erik,
                R.raw.yeuduongkhoquathichayvekhocvoianh));

        Util.listSong.add(new Song("Từng thương",
                "Phan Duy Anh",
                R.drawable.tungthuong_phanduyanh,
                R.raw.tungthuong_phanduyanh));

        Util.listSong.add(new Song("Yêu em hơn mỗi ngày",
                "Andiez",
                R.drawable.yeuemhonmoingay_andiez,
                R.raw.yeuemhonmoingay));
    }
}
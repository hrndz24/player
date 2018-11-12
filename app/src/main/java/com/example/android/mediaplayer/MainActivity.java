package com.example.android.mediaplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);

        final ArrayList<com.example.android.mediaplayer.Song> songs = new ArrayList<>();

        songs.add(new com.example.android.mediaplayer.Song("not today", R.raw.not_today));
        songs.add(new com.example.android.mediaplayer.Song("fire", R.raw.fire));
        songs.add(new com.example.android.mediaplayer.Song("idol", R.raw.idol));
        songs.add(new com.example.android.mediaplayer.Song("mic drop", R.raw.mic_drop));
        songs.add(new com.example.android.mediaplayer.Song("silver spoon", R.raw.silver_spoon));
        songs.add(new com.example.android.mediaplayer.Song("we are bulletproof", R.raw.we_are_bulletproof));



        //Creating an adapter of Word class
        com.example.android.mediaplayer.SongAdapter adapter = new com.example.android.mediaplayer.SongAdapter(MainActivity.this, songs);

        //Making a ListView in NumbersActivity
        ListView listView = (ListView) findViewById(R.id.songs_list);

        //Setting the adapter on NumbersActivity ListView
        listView.setAdapter(adapter);

    }
}
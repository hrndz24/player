package com.example.android.mediaplayer;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<com.example.android.mediaplayer.Song> {

    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private int currentSong;

    public SongAdapter(Activity context, ArrayList<com.example.android.mediaplayer.Song> songs) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songs);
        //the activity we are in
        mContext=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        //the song that user has clicked on
        final com.example.android.mediaplayer.Song song = getItem(position);

        final Button playButton = (Button) listItemView.findViewById(R.id.play_button);
        playButton.setText("Play");

        //setting an event listener on play button
        playButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {   //if the mediaPlayer is not used now it turns on the song

                if(mMediaPlayer==null) {
                    mMediaPlayer = MediaPlayer.create(mContext, song.getSongResource());
                    mMediaPlayer.start();
                    Toast.makeText(mContext, song.getSongName(), Toast.LENGTH_LONG).show();
                    //initialises the currentSong to be the resource of the song playing
                    currentSong = song.getSongResource();

                } else{
                    // if the song that has been stopped is not the one the user clicked on
                    // it cleans the mediaPLayer and starts a new one and places the resource
                    // into the currentSong variable
                    if(currentSong!=song.getSongResource()){
                        mMediaPlayer.release();
                        mMediaPlayer = MediaPlayer.create(mContext, song.getSongResource());
                        mMediaPlayer.start();
                        Toast.makeText(mContext, song.getSongName(), Toast.LENGTH_LONG).show();
                        currentSong = song.getSongResource();
                    }
                    // if the song that has been stopped was clicked on
                    else {
                        mMediaPlayer.start();
                        Toast.makeText(mContext, song.getSongName(), Toast.LENGTH_LONG).show();
                    }
                }
                // when the song stops it starts playing the next one
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if(mMediaPlayer!= null){
                            mMediaPlayer.release();
                            currentSong = getItem(position +1).getSongResource();
                            mMediaPlayer = MediaPlayer.create(mContext, currentSong);
                            mMediaPlayer.start();
                            Toast.makeText(mContext, song.getSongName(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        final Button pauseButton = (Button) listItemView.findViewById(R.id.pause_button);
        pauseButton.setText("Pause");
        // setting an event listener on pause button
        pauseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // checks if the user has clicked on the pause button of the song that is playing
                if (currentSong==song.getSongResource()){
                    mMediaPlayer.pause();
                }
            }
        });

        final Button playAgainButton = (Button) listItemView.findViewById(R.id.play_again_button);
        playAgainButton.setText(song.getSongName());
        // setting an event listener on the play again button
        playAgainButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // checks if mediaPlayer is empty and cleans it
                if(mMediaPlayer != null){
                    mMediaPlayer.release();
                }
                // starts playing the song
                mMediaPlayer = MediaPlayer.create(mContext, song.getSongResource());
                mMediaPlayer.start();
                Toast.makeText(mContext, song.getSongName(), Toast.LENGTH_LONG).show();
                currentSong = song.getSongResource();

                // when the song stops it starts playing the next one
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if(mMediaPlayer!= null){
                            mMediaPlayer.release();
                            currentSong = getItem(position +1).getSongResource();
                            mMediaPlayer = MediaPlayer.create(mContext, currentSong);
                            mMediaPlayer.start();
                            Toast.makeText(mContext, song.getSongName(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        // Return the whole list item layout (containing 3 ButtonViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}




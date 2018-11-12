package com.example.android.mediaplayer;

/**
 * This class contains information about the song
 *
 * @param mSongName refers to the name of the song
 * @param mSongResource is the resource of the song
 */
public class Song {

    private String mSongName;
    private int mSongResource;

    //Creates a Song object which includes the name and the resource ID of the song
    public Song(String songName, int songResource) {
        mSongName = songName;
        mSongResource = songResource;
    }

    public String getSongName(){
        return mSongName;
    }

    public int getSongResource(){
        return mSongResource;
    }

}

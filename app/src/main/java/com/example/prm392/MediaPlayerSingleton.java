package com.example.prm392;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerSingleton {
    private static MediaPlayer mediaPlayer;

    private MediaPlayerSingleton() {
        // Private constructor to prevent instantiation
    }

    public static MediaPlayer getInstance(Context context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), R.raw.bg_music);
        }
        return mediaPlayer;
    }

    public static void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public static void pause(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public static void resume(){
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}

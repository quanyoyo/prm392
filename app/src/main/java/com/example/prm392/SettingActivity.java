package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class SettingActivity extends AppCompatActivity {
    private static final String PREF_NAME = "MyPreferences";
    private static final String PREF_MUTED = "isMuted";
    private static final String PREF_VOLUME = "volumeLevel";
    private SharedPreferences sharedPreferences;
    private AudioManager audioManager;
    private SeekBar volumeSeekBar;
    private CheckBox muteCheckBox;
    private ImageButton btnBack;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayerSingleton.getInstance(this);
        mediaPlayer.start();
        //loop
        mediaPlayer.setLooping(true);

        // Initialize the AudioManager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Initialize the mute checkbox
        muteCheckBox = findViewById(R.id.muteCheckBox);

        // Initialize the volume SeekBar
        volumeSeekBar = findViewById(R.id.volumeSeekBar);

        // Set the maximum volume for the SeekBar
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeSeekBar.setMax(maxVolume);

        // Get the saved volume level from SharedPreferences
        int savedVolumeLevel = sharedPreferences.getInt(PREF_VOLUME, 0);
        volumeSeekBar.setProgress(savedVolumeLevel);

        // Restore saved state from SharedPreferences
        boolean isMuted = sharedPreferences.getBoolean(PREF_MUTED, false);
        muteCheckBox.setChecked(isMuted);
        volumeSeekBar.setProgress(sharedPreferences.getInt(PREF_VOLUME, 0));

        // Set muteCheckBox listener
        muteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update volumeSeekBar and its progress based on mute state
                if (isChecked) {
                    volumeSeekBar.setProgress(0);
                } else {
                    int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    if(currentVolume==0){
                        currentVolume=4;
                    }
                    volumeSeekBar.setProgress(currentVolume);
                }

                // Save mute state in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(PREF_MUTED, isChecked);
                editor.apply();
            }
        });

        // Set volumeSeekBar listener
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Adjust muteCheckBox based on progress value
                if (progress > 0) {
                    muteCheckBox.setChecked(false);
                }else if(progress==0){
                    muteCheckBox.setChecked(true);
                }

                // Update volume level
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

                // Save volume level in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(PREF_VOLUME, progress);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No implementation needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No implementation needed
            }
        });

        btnBack = ((ImageButton) findViewById(R.id.btn_back));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerSingleton.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaPlayerSingleton.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources
        MediaPlayerSingleton.release();
    }
}
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
    private SharedPreferences sharedPreferences;
    private AudioManager audioManager;
    private SeekBar volumeSeekBar;
    private static final String PREF_NAME = "MyPreferences";
    private static final String PREF_MUTED = "isMuted";
    private static final String PREF_VOLUME = "volumeLevel";


    private CheckBox muteCheckBox;
    private CheckBox darkModeBox;
    private ImageButton btnBack;

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music);
//        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // Initialize the AudioManager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Initialize the mute checkbox
        muteCheckBox = findViewById(R.id.muteCheckBox);
//        darkModeBox = findViewById(R.id.darkModeCheckBox);

        // Initialize the volume SeekBar
        volumeSeekBar = findViewById(R.id.volumeSeekBar);

        // Set the initial checkbox state based on the saved preference
        boolean isMuted = sharedPreferences.getBoolean(PREF_MUTED, false);


        // Set the maximum volume for the SeekBar
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeSeekBar.setMax(maxVolume);

        // Get the saved volume level from SharedPreferences
        int savedVolumeLevel = sharedPreferences.getInt(PREF_VOLUME, 0);
        volumeSeekBar.setProgress(savedVolumeLevel);

        // Set the volume change listener
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

                // Save the volume level in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(PREF_VOLUME, progress);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No action needed
            }
        });

        isMuted = sharedPreferences.getBoolean(PREF_MUTED, (savedVolumeLevel<=0)?true:false);
        muteCheckBox.setChecked(isMuted);

        // Set the mute checkbox listener
        muteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save the mute state in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
//                Log.d("isChecked", "isChecked: "+isChecked);
                editor.putBoolean(PREF_MUTED, isChecked);
                editor.apply();

                // Adjust the media player volume based on the mute state
                if (isChecked) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                } else {
                    // Set the desired volume level when not muted
                    int desiredVolume = savedVolumeLevel; // Change to your preferred volume level
                    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                    int volume = (int) (maxVolume * (desiredVolume / 10.0));
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
                }
            }
        });

        darkModeBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Enable dark mode
                    savePreferences(muteCheckBox.isChecked(), darkModeBox.isChecked());
                } else {
                    // Disable dark mode
                    // TODO: Implement dark mode disable logic
                }
            }
        });

//        if (!isMuted) {
//            mediaPlayer.start();
//        }
        btnBack = ((ImageButton) findViewById(R.id.btn_back));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
    }
    public void goToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        // Release the MediaPlayer resources
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }


    private void savePreferences(boolean isMuted, boolean isDarkMode) {
        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        editor.putBoolean("isMuted", isMuted);
        editor.putBoolean("isDarkMode", isDarkMode);
        editor.apply();
    }
    @Override
    protected void onDestroy () {
        super.onDestroy();
        // Release the MediaPlayer resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }
}
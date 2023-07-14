package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button settingButton;
    private Button restartButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        retrieve MediaPlayer instance
        mediaPlayer = MediaPlayerSingleton.getInstance(this);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        startButton = findViewById(R.id.startButton);
        restartButton = findViewById(R.id.btn_restart);
        settingButton = findViewById(R.id.settingButton);

        // Check if the game has been previously saved
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        boolean isGameSaved = prefs.getBoolean("isGameSaved", false);

        if (isGameSaved) {
            // If the game was saved, enable the restart button
            restartButton.setEnabled(true);
        } else {
            // If the game was not saved, disable the restart button
            restartButton.setEnabled(false);
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the saved game data
                clearSavedGameData();
                Story.deleteAllItems();
                // Start the game activity
                startGame();
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stop the music playback
//                mediaPlayer.stop();
                // Release the MediaPlayer resources
//                MediaPlayerSingleton.release();
                //open settings
                openSettings();
            }
        });
    }

    private void clearSavedGameData() {
        // Clear the saved game data from shared preferences or any other storage mechanism
        SharedPreferences.Editor editor = getSharedPreferences("GamePrefs", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    private void startGame() {
        // TODO: Add code to start the game activity
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingActivity.class);
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
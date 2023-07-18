package com.example.prm392;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button settingButton;
    private Button restartButton;
    private Button recordButton;
    private MediaPlayer mediaPlayer;
    private static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(getApplicationContext(), Database.class, "db-game")
                .allowMainThreadQueries().build();
//        retrieve MediaPlayer instance
        mediaPlayer = MediaPlayerSingleton.getInstance(this);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        startButton = findViewById(R.id.startButton);
        restartButton = findViewById(R.id.btn_restart);
        settingButton = findViewById(R.id.settingButton);
        recordButton = findViewById(R.id.btn_record);

        // Check if the game has been previously saved
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        boolean isGameSaved = prefs.getBoolean("isGameSaved", false);

        if (isGameSaved) {
            // If the game was saved, enable the restart button
            startButton.setText("Resume");
            restartButton.setEnabled(true);
        } else {
            // If the game was not saved, disable the restart button
            startButton.setText("Start");
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
               showRestartPopupDialog();
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
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecord();
            }
        });
    }

    public static Database getDatabase() {
        return database;
    }
    private void clearSavedGameData() {
        // Clear the saved game data from shared preferences or any other storage mechanism
        SharedPreferences.Editor editor = getSharedPreferences("GamePrefs", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    private void showRestartPopupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restart the game");
        builder.setMessage("Are you sure");

        // Add any additional customization to the dialog here, such as buttons, etc.

        // Set a click listener for the "Resume" button (optional)
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle yes functionality
                // Clear the saved game data
                clearSavedGameData();
//                Story.secondTime=false;
                if(!database.itemDao().getAll().isEmpty()){
                    Story.deleteAllItems();
                }
                // Start the game activity
                startGame();
//                Log.d("btn",""+((Button)v).getText());
            }
        });

        // Set a click listener for the "Quit" button (optional)
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle no functionality
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void startGame() {
        // TODO: Add code to start the game activity
        Intent intent = new Intent(this, GameScreen.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, startButton, "slide_transition");
        startActivity(intent, options.toBundle());
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, startButton, "slide_transition");
        startActivity(intent, options.toBundle());
    }
    private void openRecord(){
        Intent intent = new Intent(this, GameRecordScreen.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, startButton, "slide_transition");
        startActivity(intent, options.toBundle());
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
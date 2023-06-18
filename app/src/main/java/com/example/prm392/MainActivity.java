package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        settingButton = findViewById(R.id.settingButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
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

}
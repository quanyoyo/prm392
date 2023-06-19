package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

public class SettingActivity extends AppCompatActivity {

    private CheckBox muteCheckBox;
    private ImageButton btnBack;
    GameScreen gs = new GameScreen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        muteCheckBox = findViewById(R.id.muteCheckBox);

        // Set the initial state of the mute checkbox based on the user's preferences
        boolean isMuted = getPreferences(Context.MODE_PRIVATE).getBoolean("isMuted", false);
        muteCheckBox.setChecked(isMuted);

        muteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveMutePreference(isChecked);
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

    private void saveMutePreference(boolean isMuted) {
        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        editor.putBoolean("isMuted", isMuted);
        editor.apply();
    }
    public void goToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
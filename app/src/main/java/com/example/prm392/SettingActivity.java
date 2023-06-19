package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingActivity extends AppCompatActivity {

    private CheckBox muteCheckBox;
    private CheckBox darkModeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        muteCheckBox = findViewById(R.id.muteCheckBox);
        darkModeBox = findViewById(R.id.darkModeCheckBox);

        // Set the initial state of the mute checkbox based on the user's preferences
        boolean isMuted = getPreferences(Context.MODE_PRIVATE).getBoolean("isMuted", false);
        muteCheckBox.setChecked(isMuted);

        muteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                savePreferences(muteCheckBox.isChecked(), darkModeBox.isChecked());
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
    }

    private void savePreferences(boolean isMuted, boolean isDarkMode) {
        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        editor.putBoolean("isMuted", isMuted);
        editor.putBoolean("isDarkMode", isDarkMode);
        editor.apply();
    }


}
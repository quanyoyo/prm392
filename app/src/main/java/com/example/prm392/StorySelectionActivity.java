package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class StorySelectionActivity extends AppCompatActivity {
    TextView tv_game_head, tv_game_content;
    ImageView img;
    Button button1, button2, button3, button4;

    Story story = new Story(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_selection);

        tv_game_head = ((TextView)findViewById(R.id.tv_game_head));
        tv_game_content = ((TextView)findViewById(R.id.tv_game_content));
        img = (ImageView) findViewById(R.id.img);
        button1 = ((Button)findViewById(R.id.button1));
        button2 = ((Button)findViewById(R.id.button2));
        button3 = ((Button)findViewById(R.id.button3));
        button4 = ((Button)findViewById(R.id.button4));

        story.startPoint();

        // Check the mute preference and handle sound accordingly
        boolean isMuted = getPreferences(Context.MODE_PRIVATE).getBoolean("isMuted", false);
        if (isMuted) {
            // Mute the sound
        } else {
            // Unmute the sound
        }
    }

    public void chooseButton1(View view){
        story.selectPosition(story.nextPos1);
    }
    public void chooseButton2(View view){
        story.selectPosition(story.nextPos2);
    }
    public void chooseButton3(View view){
        story.selectPosition(story.nextPos3);
    }
    public void chooseButton4(View view){
        story.selectPosition(story.nextPos4);
    }

}
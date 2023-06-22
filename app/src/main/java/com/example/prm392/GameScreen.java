package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    TextView tv_game_head, tv_game_content;
    ImageView img;
    Button btn1, btn2, btn3, btn4;

    Story story = new Story(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        tv_game_head = ((TextView)findViewById(R.id.tv_game_head));
        tv_game_content = ((TextView)findViewById(R.id.tv_game_content));
        img = (ImageView) findViewById(R.id.img);
        btn1 = ((Button)findViewById(R.id.btn1));
        btn2 = ((Button)findViewById(R.id.btn2));
        btn3 = ((Button)findViewById(R.id.btn3));
        btn4 = ((Button)findViewById(R.id.btn4));

        story.startingPoint();
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
    public void goToTitle() {
        Intent titleScreen = new Intent(this, MainActivity.class);
        startActivity(titleScreen);
    }
}
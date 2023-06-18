package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    ImageView image;
    TextView text;
    Button btn1, btn2, btn3, btn4;
    Story story = new Story(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        image = (ImageView) findViewById(R.id.gameImageView);
        text = (TextView) findViewById(R.id.tv_script);
        btn1 = (Button) findViewById(R.id.btn_choice1);
        btn2 = (Button) findViewById(R.id.btn_choice2);
        btn3 = (Button) findViewById(R.id.btn_choice3);
        btn4 = (Button) findViewById(R.id.btn_choice4);

        story.startingPoint();
    }

    public void btn1(View view){
        story.selectPosition(story.nextPos1);
    }
    public void btn2(View view){
        story.selectPosition(story.nextPos2);
    }
    public void btn3(View view){
        story.selectPosition(story.nextPos3);
    }
    public void btn4(View view){
        story.selectPosition(story.nextPos4);
    }

}
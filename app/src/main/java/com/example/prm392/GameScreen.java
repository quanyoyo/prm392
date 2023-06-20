package com.example.prm392;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    TextView tv_game_head, tv_game_content;
    ImageView img;
    Button btn1, btn2, btn3, btn4;
    Story story;
    ImageView btnPause;



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
//        Player player = new Player(story.atk, story.def);
//        tv_game_head.setText(Integer.toString(player.getAttack()));
        btnPause = ((ImageView) findViewById(R.id.btn_pause));

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupDialog();
            }
        });

        story = new Story(this, getApplicationContext());

        resumeGame();

        // Check the mute preference and handle sound accordingly
        boolean isMuted = getPreferences(Context.MODE_PRIVATE).getBoolean("isMuted", false);
        if (isMuted) {
            // Mute the sound
            //
        } else {
            // Unmute the sound
        }
    }

    private void showPopupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Paused");
        builder.setMessage("Your game is paused.");

        // Add any additional customization to the dialog here, such as buttons, etc.

        // Set a click listener for the "Resume" button (optional)
        builder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle resume functionality
            }
        });

        // Set a click listener for the "Quit" button (optional)
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle quit functionality
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void resumeGame() {
        // Check if the game has been previously saved
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        boolean isGameSaved = prefs.getBoolean("isGameSaved", false);

        if (isGameSaved) {
            // If the game was saved, resume the game
            story.startOrResumeGame();
        } else {
            // If the game was not saved, start a new game
            story.startingPoint();
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
    public void goToTitle() {
        // Save the game state before going back to the title screen
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isGameSaved", true);
        editor.apply();

        Intent titleScreen = new Intent(this, MainActivity.class);
        startActivity(titleScreen);
    }

//    public void showPopup() {
//        // Anchor popoup with layout to "center" menu
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.menuLayout);
//        PopupMenu popup = new PopupMenu(this, layout);
//        popup.setOnMenuItemClickListener(this);
//        popup.getMenuInflater().inflate(R.layout.menu, popup.getMenu());
//        popup.show();
//    }


}
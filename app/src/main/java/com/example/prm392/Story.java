package com.example.prm392;

import android.view.View;
public class Story {
    GameScreen gs;
    String nextPos1, nextPos2, nextPos3, nextPos4;

    public Story(GameScreen gameScreen) {
        this.gs = gameScreen;
    }
    public void selectPosition(String pos){
        switch (pos){
            case "startPoint": startPoint();break;
            case "guide": guide();break;
        }
    }
    public void showAllButton(){
        gs.button1.setVisibility(View.VISIBLE);
        gs.button2.setVisibility(View.VISIBLE);
        gs.button3.setVisibility(View.VISIBLE);
        gs.button4.setVisibility(View.VISIBLE);
    }
    public void startPoint(){
        gs.tv_game_content.setText("screen1");

        gs.button1.setText("Do 1");
        gs.button2.setText("Do 2");
        gs.button3.setText("Do 3");
        gs.button4.setText("Guide");

        showAllButton();
        nextPos1="get 1";
        nextPos2="get 2";
        nextPos3="get 3";
        nextPos4="guide";
    }
    public void guide(){
        gs.tv_game_content.setText("Blah blah");

        gs.button1.setText("Back");
        gs.button2.setText("");
        gs.button3.setText("");
        gs.button4.setText("");

        gs.button2.setVisibility(View.INVISIBLE);
        gs.button3.setVisibility(View.INVISIBLE);
        gs.button4.setVisibility(View.INVISIBLE);

        nextPos1="startPoint";
        nextPos2="";
        nextPos3="";
        nextPos4="";
    }
}

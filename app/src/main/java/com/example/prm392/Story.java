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
        gs.btn1.setVisibility(View.VISIBLE);
        gs.btn2.setVisibility(View.VISIBLE);
        gs.btn3.setVisibility(View.VISIBLE);
        gs.btn4.setVisibility(View.VISIBLE);
    }
    public void startPoint(){
        gs.tv_game_content.setText("screen1");

        gs.btn1.setText("Do 1");
        gs.btn2.setText("Do 2");
        gs.btn3.setText("Do 3");
        gs.btn4.setText("Guide");

        showAllButton();
        nextPos1="get 1";
        nextPos2="get 2";
        nextPos3="get 3";
        nextPos4="guide";
    }
    public void guide(){
        gs.tv_game_content.setText("Blah blah");

        gs.btn1.setText("Back");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1="startPoint";
        nextPos2="";
        nextPos3="";
        nextPos4="";
    }
}

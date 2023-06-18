package com.example.prm392;

import android.view.View;
public class Story {
    StorySelectionActivity storySelectionActivity;
    String nextPos1, nextPos2, nextPos3, nextPos4;

    public Story(StorySelectionActivity storySelectionActivity) {
        this.storySelectionActivity = storySelectionActivity;
    }
    public void selectPosition(String pos){
        switch (pos){
            case "startPoint": startPoint();break;
            case "guide": guide();break;
        }
    }
    public void showAllButton(){
        storySelectionActivity.button1.setVisibility(View.VISIBLE);
        storySelectionActivity.button2.setVisibility(View.VISIBLE);
        storySelectionActivity.button3.setVisibility(View.VISIBLE);
        storySelectionActivity.button4.setVisibility(View.VISIBLE);
    }
    public void startPoint(){
        storySelectionActivity.tv_game_content.setText("screen1");

        storySelectionActivity.button1.setText("Do 1");
        storySelectionActivity.button2.setText("Do 2");
        storySelectionActivity.button3.setText("Do 3");
        storySelectionActivity.button4.setText("Guide");

        showAllButton();
        nextPos1="get 1";
        nextPos2="get 2";
        nextPos3="get 3";
        nextPos4="guide";
    }
    public void guide(){
        storySelectionActivity.tv_game_content.setText("Blah blah");

        storySelectionActivity.button1.setText("Back");
        storySelectionActivity.button2.setText("");
        storySelectionActivity.button3.setText("");
        storySelectionActivity.button4.setText("");

        storySelectionActivity.button2.setVisibility(View.INVISIBLE);
        storySelectionActivity.button3.setVisibility(View.INVISIBLE);
        storySelectionActivity.button4.setVisibility(View.INVISIBLE);

        nextPos1="startPoint";
        nextPos2="";
        nextPos3="";
        nextPos4="";
    }
}

package com.example.prm392;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;


public class Story {

    GameScreen gs;
    String nextPos1, nextPos2, nextPos3, nextPos4;
    boolean gun = false;
    boolean keycard = false;
    boolean secondTime = false;
    Player player;
    private String currentPlayerPosition;
    private static final String PREFS_NAME = "GamePrefs";
    private static final String KEY_GAME_SAVED = "isGameSaved";
    private static final String KEY_POSITION = "CurrentPosition";
    private static final String KEY_GUN = "HasGun";
    private static final String KEY_KEYCARD = "HasKeycard";
    private static final String KEY_SECOND_TIME = "IsSecondTime";

    private Context context;

    public Story(GameScreen gs, Context context) {
    Player player = new Player();
        this.gs = gs;
        this.context = context;
        //player = new Player();
    }

    public String getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public void setCurrentPlayerPosition(String currentPlayerPosition) {
        this.currentPlayerPosition = currentPlayerPosition;
    }

    public void selectPosition(String pos) {
        currentPlayerPosition = pos;
        saveGameState();
        switch (pos) {
            case "startingPoint":
                startingPoint();
                break;
            case "weapon":
                weapon();
                break;
            case "stay":
                stay();
                break;
            case "openChest":
                openChest();
                break;
            case "goToTitle":
                gs.goToTitle();
                break;
            case "alien":
                alien();
                break;
            case "killed":
                killed();
                break;
            case "keycard":
                keycard();
                break;
            case "elevator":
                elevator();
                break;
            case "locked":
                locked();
                break;
            case "callElevator":
                callElevator();
                break;
        }
    }

    public void saveGameState() {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_GAME_SAVED, true);
        editor.putString(KEY_POSITION, currentPlayerPosition);
        editor.putBoolean(KEY_GUN, gun);
        editor.putBoolean(KEY_KEYCARD, keycard);
        editor.putBoolean(KEY_SECOND_TIME, secondTime);
        editor.apply();
    }

    public void loadGameState() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isGameSaved = prefs.getBoolean(KEY_GAME_SAVED, false);
        if (isGameSaved) {
            currentPlayerPosition = prefs.getString(KEY_POSITION, "startingPoint");
            gun = prefs.getBoolean(KEY_GUN, false);
            keycard = prefs.getBoolean(KEY_KEYCARD, false);
            secondTime = prefs.getBoolean(KEY_SECOND_TIME, false);
        }
    }

    public void restartGame() {
        // Clear the shared preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();

        // Reset the game state variables
        currentPlayerPosition = "startingPoint";
        gun = false;
        keycard = false;
        secondTime = false;

        // Start the game from the beginning
        startingPoint();
    }

    public void startOrResumeGame() {
        loadGameState();
        selectPosition(currentPlayerPosition);
    }
    public void showALlButtons() {
    boolean chestOpened = false;
    boolean alienDead = false;

    public Story(GameScreen gs) {
        this.gs = gs;
    }

    public void selectPosition(String pos){
        switch (pos){
            case "startingPoint": startingPoint(); break;
            case "weapon": weapon(); break;
            case "stay": stay(); break;
            case "openChest": openChest(); break;
            case "goToTitle": gs.goToTitle(); break;
            case "alien": alien(); break;
            case "killed": killed(); break;
            case "keycard": keycard(); break;
            case "elevator": elevator(); break;
            case "locked": locked(); break;
            case "callElevator": callElevator(); break;
        }
    }

    public void showALlButtons(){
        gs.btn1.setVisibility(View.VISIBLE);
        gs.btn2.setVisibility(View.VISIBLE);
        gs.btn3.setVisibility(View.VISIBLE);
        gs.btn4.setVisibility(View.VISIBLE);
    }


    public void startingPoint(){
        if(secondTime == false){
            gs.image.setImageResource(R.drawable.airtighthatch);
            gs.text.setText("You woke up to an unfamiliar ceiling. There are 3 doors.\n\nWhich one will you take?");
            gs.btn1.setText("The one in front");
            gs.btn2.setText("The left one");
            gs.btn3.setText("The right one");
            gs.btn4.setText("Stay still and think for a while");
            showALlButtons();

            nextPos1 = "elevator";
            nextPos2 = "alien";
            nextPos3 = "weapon";
            nextPos4 = "stay";
            secondTime = true;
        }else{
            gs.image.setImageResource(R.drawable.airtighthatch);
            gs.text.setText("Back to where you woke up. There are still 3 doors.\n\nWhich one will you take?");
            gs.btn1.setText("The one in front");
            gs.btn2.setText("The left one");
            gs.btn3.setText("The right one");
            gs.btn4.setText("Stay still and think for a while");
            showALlButtons();

            nextPos1 = "elevator";
            nextPos2 = "alien";
            nextPos3 = "weapon";
            nextPos4 = "stay";
        }
        saveGameState();
    }

    public void elevator() {
        gs.img.setImageResource(R.drawable.elevator);

        gs.tv_game_content.setText("There is an elevator ahead. Looks like it requires a keycard");

        gs.btn1.setText("Try to call the elevator");
        gs.btn2.setText("Go back");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        if (!keycard) {
            nextPos1 = "locked";
        } else {
            nextPos1 = "callElevator";
        }
        nextPos2 = "startingPoint";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void locked() {
        gs.img.setImageResource(R.drawable.padlock);

        gs.tv_game_content.setText("Seems like you cannot call the elevator without some sort of keycard. Try to look for one.");

        gs.btn1.setText("Go back");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "startingPoint";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void callElevator() {
        gs.image.setImageResource(R.drawable.elevatorfloor);

        gs.text.setText("The elevator arrived. You got inside. Seems like you are on the first floor. Where will you go?");

        gs.btn1.setText("Second floor");
        gs.btn2.setText("Third floor");
        gs.btn3.setText("Go back");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.VISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "secondFloor";
        nextPos2 = "thirdFloor";
        nextPos3 = "startingPoint";
        nextPos4 = "";
    }

    public void alien(){
        if(alienDead == false){
            gs.image.setImageResource(R.drawable.metroid);

            gs.text.setText("You encountered an alien! What shall you do?");

            gs.btn1.setText("FIGHT!");
            gs.btn2.setText("Run");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            if(gun==false){
                nextPos1 = "killed";
            }else{
                nextPos1 = "keycard";
                alienDead = true;
            }
            nextPos2 = "startingPoint";
        }else{
            gs.image.setImageResource(R.drawable.halfbody);

            gs.text.setText("You came back to a room with the dead alien you killed earlier. " +
                    "You thought it stood no chance against your plot armor in this game. " +
                    "That aside, there isn't anything left to do in this room.");

            gs.btn1.setText("Go back");
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            nextPos1 = "startingPoint";
            nextPos2 = "";
        }
        nextPos3 = "";
        nextPos4 = "";


    }

    //Ending #2: Rookie Mistake
    public void killed(){

        gs.image.setImageResource(R.drawable.brokenskull);

        gs.text.setText("You tried to fight an alien without any weapons. " +
                "In hindsight, might not have been the best course of action D:\n\n GAME OVER" +
                "\n\n Ending #2: Rookie Mistake");

        gs.btn1.setText("Try Again");
        gs.btn2.setText("Back to Title");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "startingPoint";
        nextPos2 = "goToTitle";
        nextPos3 = "";
        nextPos4 = "";

    }

    public void keycard(){
        gs.image.setImageResource(R.drawable.keycard);
        keycard = true;

        gs.text.setText("You defeated the alien with the ray gun you found. You picked up a keycard from the dead alien.");

        gs.btn1.setText("Take it and go back");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "startingPoint";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void weapon(){
        if(chestOpened == false){
            gs.image.setImageResource(R.drawable.chest);

            gs.text.setText("You found a box. \n\nMight be something good inside.");

            gs.btn1.setText("Open it");
            gs.btn2.setText("Go back");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            nextPos1 = "openChest";
            nextPos2 = "startingPoint";
        }else{
            gs.image.setImageResource(R.drawable.openchest);

            gs.text.setText("You already opened the box. There is nothing else inside.");

            gs.btn1.setText("Go back");
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            nextPos1 = "startingPoint";
            nextPos2 = "";
        }
        nextPos3 = "";
        nextPos4 = "";

    }

    public void openChest(){
        gs.image.setImageResource(R.drawable.raygun);
        gun = true;
        chestOpened = true;

        gs.text.setText("You found an alien ray gun.\n\nNice, now you can defend yourself.");

        gs.btn1.setText("Take it and go back");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "startingPoint";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    //ending #1: dumb ways to die
    public void stay(){

        gs.image.setImageResource(R.drawable.disintegrate);

        gs.text.setText("You recall that you were actually kidnapped by aliens. " +
                    "Speak of the devil, an alien comes into the room and brings you to the lab. " +
                    "They do experiments on you and you die from the trauma and pain. GAME OVER\n Ending #1: Dumb Ways to Die");

        gs.btn1.setText("Try Again");
        gs.btn2.setText("Back to Title");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "startingPoint";
        nextPos2 = "goToTitle";
        nextPos3 = "";
        nextPos4 = "";

    }
}

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
    Player player = new Player(100, 0);
    private String currentPlayerPosition;
    private static final String PREFS_NAME = "GamePrefs";
    private static final String KEY_GAME_SAVED = "isGameSaved";
    private static final String KEY_POSITION = "CurrentPosition";
    private static final String KEY_GUN = "HasGun";
    private static final String KEY_KEYCARD = "HasKeycard";
    private static final String KEY_SECOND_TIME = "IsSecondTime";
    boolean chestOpened = false;
    boolean alienDead = false;

    private Context context;

    public Story(GameScreen gs, Context context) {
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
            case "startingPoint": startingPoint(); break;
            case "weapon": weapon(); break;
            case "stay": stay(); break;
            case "openChest": openChest(); break;
//            case "goToTitle": gs.goToTitle(); break;
            case "alien": alien(); break;
            case "killed": killed(); break;
            case "keycard": keycard(); break;
            case "elevator": elevator(); break;
            case "locked": locked(); break;
            case "callElevator": callElevator(); break;
            case "secondFloor": secondFloor(); break;
            case "storage": storage(); break;
            case "labs": labs(); break;
            case "thirdFloor": thirdFloor(); break;
            case "livingQuarter": livingQuarter(); break;
            case "shop": shop(); break;
            case "gunPart3": gunPart3(); break;
            case "suit": suit(); break;
            case "food": food(); break;
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


    public void showALlButtons(){
        gs.btn1.setVisibility(View.VISIBLE);
        gs.btn2.setVisibility(View.VISIBLE);
        gs.btn3.setVisibility(View.VISIBLE);
        gs.btn4.setVisibility(View.VISIBLE);
    }


    public void startingPoint(){
        if(secondTime == false){
            gs.img.setImageResource(R.drawable.airtighthatch);
            gs.tv_game_content.setText("You woke up to an unfamiliar ceiling. There are 3 doors.\n\nWhich one will you take?");
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
            gs.img.setImageResource(R.drawable.airtighthatch);
            gs.tv_game_content.setText("Back to where you woke up. There are still 3 doors.\n\nWhich one will you take?");
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
            currentFloor = 1;
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
        gs.img.setImageResource(R.drawable.elevatorfloor);
        if(currentFloor == 1){
            gs.tv_game_content.setText("The elevator arrived. You got inside. Seems like you are on the first floor. Where will you go?");

            gs.btn1.setText("Second floor");
            gs.btn2.setText("Third floor");
            gs.btn3.setText("Go back");

            nextPos1 = "secondFloor";
            nextPos2 = "thirdFloor";
            nextPos3 = "startingPoint";
        }else if(currentFloor == 2){
            gs.tv_game_content.setText("The elevator arrived. You are on the second floor. Where will you go?");

            gs.btn1.setText("First floor");
            gs.btn2.setText("Third floor");
            gs.btn3.setText("Go back");


            nextPos1 = "startingPoint";
            nextPos2 = "thirdFloor";
            nextPos3 = "secondFloor";
        }else if(currentFloor == 3){
            gs.tv_game_content.setText("The elevator arrived. You are on the third floor. Where will you go?");

            gs.btn1.setText("First floor");
            gs.btn2.setText("Second floor");
            gs.btn3.setText("Go back");

            nextPos1 = "startingPoint";
            nextPos2 = "secondFloor";
            nextPos3 = "thirdFloor";

        }
        gs.btn4.setText("");
        gs.btn3.setVisibility(View.VISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos4 = "";

    }

    public void alien(){
        if(alienDead == false){
            gs.img.setImageResource(R.drawable.metroid);

            gs.tv_game_content.setText("You encountered an alien! What shall you do?");

            gs.btn1.setText("FIGHT!");
            gs.btn2.setText("Run");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            if(!gun){
                nextPos1 = "killed";
            }else{
                nextPos1 = "keycard";
                alienDead = true;
            }
            nextPos2 = "startingPoint";
        }else{
            gs.img.setImageResource(R.drawable.halfbody);

            gs.tv_game_content.setText("You came back to a room with the dead alien you killed earlier. " +
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

        gs.img.setImageResource(R.drawable.brokenskull);

        gs.tv_game_content.setText("You tried to fight an alien without any weapons. " +
                "In hindsight, might not have been the best course of action D:\n\n GAME OVER" +
                "\n\n Ending #2: Rookie Mistake");

        gs.btn1.setText("Try Again");
        gs.btn2.setText("Back to Title");
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

    public void keycard(){
        gs.img.setImageResource(R.drawable.keycard);
        keycard = true;

        gs.tv_game_content.setText("You defeated the alien with the ray gun you found. You picked up a keycard from the dead alien.");

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
            gs.img.setImageResource(R.drawable.chest);

            gs.tv_game_content.setText("You found a box. \n\nMight be something good inside.");

            gs.btn1.setText("Open it");
            gs.btn2.setText("Go back");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            nextPos1 = "openChest";
            nextPos2 = "startingPoint";
        }else{
            gs.img.setImageResource(R.drawable.openchest);

            gs.tv_game_content.setText("You already opened the box. There is nothing else inside.");

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
        gs.img.setImageResource(R.drawable.raygun);
        gun = true;
        chestOpened = true;
        player.setMoney(player.getMoney() + 500);

        gs.tv_game_content.setText("You found an alien ray gun. Nice, now you can defend yourself." +
                "\n\nYou also found 500 credits. The amount of credits you currently own: " + player.getMoney());

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

        gs.img.setImageResource(R.drawable.disintegrate);

        gs.tv_game_content.setText("You recall that you were actually kidnapped by aliens. " +
                    "Speak of the devil, an alien comes into the room and brings you to the lab. " +
                    "They do experiments on you and you die from the trauma and pain. GAME OVER\n Ending #1: Dumb Ways to Die");

        gs.btn1.setText("Try Again");
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

    //2nd Floor
    public int currentFloor = 0; //mark the current floor the player is on (for elevator)
    public void secondFloor() {
        gs.img.setImageResource(R.drawable.airtighthatch);

        gs.tv_game_content.setText("You are at the second floor. Luckily there are signs on the doors this time." +
                "\n\nWhere will you go?");

        gs.btn1.setText("Labs");
        gs.btn2.setText("Prison");
        gs.btn3.setText("Storage");
        gs.btn4.setText("Elevator");

        showALlButtons();
        currentFloor = 2;

        nextPos1 = "labs";
        nextPos2 = "prison";
        nextPos3 = "storage";
        nextPos4 = "callElevator";
    }

    public boolean storageEntered = false;
    public void storage(){
        if(storageEntered == false){
            gs.img.setImageResource(R.drawable.brokenassaultrifle);
            storageEntered = true;
            //give player 600 credits
            player.setMoney(player.getMoney() + 600);
            gs.tv_game_content.setText("You picked up Gun Part #1 and 600 credits. " +
                    "Seems like you need to collect a few more parts to craft a better gun." +
                    "\n\nThe amount of credits you currently own: " + player.getMoney());

        }else{
            gs.img.setImageResource(R.drawable.openchest);
            gs.tv_game_content.setText("There is nothing left in this room. Why did you come back here?");

        }
        gs.btn1.setText("Go back");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");
        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos1 = "secondFloor";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";

    }

    public boolean isLabCleared = false;
    public void labs(){
        if(isLabCleared == false){
            gs.img.setImageResource(R.drawable.chemicaltank);
            gs.tv_game_content.setText("You entered the labs. It seems like the aliens are about to do an experiment " +
                    "on a human. What shall you do?");

            gs.btn1.setText("Fight the aliens");
            gs.btn2.setText("Try to sneak your way in");
            gs.btn3.setText("Get out for now");
            gs.btn4.setText("");

            gs.btn4.setVisibility(View.INVISIBLE);
            nextPos1 = "gunBlazing";
            nextPos2 = "sneakLabs";
            nextPos3 = "secondFloor";
            nextPos4 = "";
        }else{
            gs.img.setImageResource(R.drawable.chemicaltank);
            gs.tv_game_content.setText("You entered the labs. There are just corpses of dead aliens lying around. " +
                    "They won't be experimenting on anything anytime soon - you thought.");

            gs.btn1.setText("Go back");
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);
            nextPos1 = "secondFloor";
            nextPos2 = "";
            nextPos3 = "";
            nextPos4 = "";
        }

    }


    //third floor content
    public void thirdFloor() {
        gs.img.setImageResource(R.drawable.airtighthatch);

        gs.tv_game_content.setText("You are at the third floor. You feel like this is where things all end." +
                "\n\nWhere shall you go?");

        gs.btn1.setText("Hangar");
        gs.btn2.setText("Communication Center");
        gs.btn3.setText("Alien Living Quarter");
        gs.btn4.setText("Elevator");

        showALlButtons();
        currentFloor = 3;

        nextPos1 = "hangar";
        nextPos2 = "commsCenter";
        nextPos3 = "livingQuarter";
        nextPos4 = "callElevator";
    }

    public void livingQuarter() {
        gs.img.setImageResource(R.drawable.door);

        gs.tv_game_content.setText("This is where the aliens live on the ship.\n\nWhat do you want to do here?");

        gs.btn1.setText("Buy from vending machine");
        gs.btn2.setText("Go to Room #1");
        gs.btn3.setText("Go to Room #2");
        gs.btn4.setText("Go back");

        showALlButtons();

        nextPos1 = "shop";
        nextPos2 = "room1";
        nextPos3 = "room2";
        nextPos4 = "thirdFloor";
    }

    public boolean isGunPart3Bought = false;
    public boolean isFoodBought = false;
    public boolean isSuitBought = false;
    public void shop() {
        gs.img.setImageResource(R.drawable.vendingmachine);

        gs.tv_game_content.setText("Alien vending machine huh? Not so different from ones from Earth." +
                "\n\nWhat do you want to buy?");

        gs.btn1.setText("Gun Part: 500 credits");
        gs.btn2.setText("Stealth Suit: 1000 credits");
        gs.btn3.setText("Food: 100 credits");
        gs.btn4.setText("Do not buy anything");

        showALlButtons();
        if(isGunPart3Bought == true) gs.btn1.setVisibility(View.INVISIBLE);
        if(isSuitBought == true) gs.btn2.setVisibility(View.INVISIBLE);
        if(isFoodBought == true) gs.btn3.setVisibility(View.INVISIBLE);

        nextPos1 = "gunPart3";
        nextPos2 = "suit";
        nextPos3 = "food";
        nextPos4 = "thirdFloor";
    }

    public void gunPart3(){
        if(player.getMoney() >= 500){
            gs.img.setImageResource(R.drawable.brokenassaultrifle);
            isGunPart3Bought = true;
            player.setMoney(player.getMoney() - 500);
            gs.tv_game_content.setText("You bought and acquired Gun Part #3." +
                    "\n\n The amount of credits you currently own: " + player.getMoney());

            gs.btn1.setText("Keep buying");

        }else{
            gs.img.setImageResource(R.drawable.nomoney);
            gs.tv_game_content.setText("You do not have enough credits.\nNeed: 500 credits" +
                    "\nYou have: " + player.getMoney() + " credits");

            gs.btn1.setText("Buy something else");
            gs.btn1.setVisibility(View.VISIBLE);

        }
        gs.btn2.setText("Stop buying");
        gs.btn3.setText("");
        gs.btn4.setText("");
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos1 = "shop";
        nextPos2 = "thirdFloor";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void suit(){
        if(player.getMoney() >= 1000){
            gs.img.setImageResource(R.drawable.invisible);
            isSuitBought = true;
            player.setMoney(player.getMoney() - 1000);
            gs.tv_game_content.setText("You bought and acquired Stealth Suit." +
                    "\n\n The amount of credits you currently own: " + player.getMoney());

            gs.btn1.setText("Keep buying");

        }else{
            gs.img.setImageResource(R.drawable.nomoney);
            gs.tv_game_content.setText("You do not have enough credits.\nNeed: 1000 credits" +
                    "\nYou have: " + player.getMoney() + " credits");

            gs.btn1.setText("Buy something else");
            gs.btn1.setVisibility(View.VISIBLE);
        }
        gs.btn2.setText("Stop buying");
        gs.btn3.setText("");
        gs.btn4.setText("");
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos1 = "shop";
        nextPos2 = "thirdFloor";
        nextPos3 = "";
        nextPos4 = "";

    }

    public void food(){
        if(player.getMoney() >= 100){
            gs.img.setImageResource(R.drawable.cannedfood);
            isFoodBought = true;
            player.setMoney(player.getMoney() - 100);
            gs.tv_game_content.setText("You bought and acquired Canned Food.\nThis is alien food, " +
                    "you don't know whether it's safe to eat. You wonder why you even bought this item." +
                    "\nThe amount of credits you currently own: " + player.getMoney());

            gs.btn1.setText("Keep buying");

        }else{
            gs.img.setImageResource(R.drawable.nomoney);
            gs.tv_game_content.setText("You do not have enough credits.\nNeed: 100 credits" +
                    "\nYou have: " + player.getMoney() + " credits");

            gs.btn1.setText("Buy something else");
            gs.btn1.setVisibility(View.VISIBLE);
        }
        gs.btn2.setText("Stop buying");
        gs.btn3.setText("");
        gs.btn4.setText("");
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos1 = "shop";
        nextPos2 = "thirdFloor";
        nextPos3 = "";
        nextPos4 = "";

    }

}

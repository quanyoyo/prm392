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
            case "gunBlazing": gunBlazing(); break;
            case "sneakLabs": sneakLabs(); break;
            case "rescueSuccessful": rescueSuccessful(); break;
            case "prison": prison(); break;
            case "wardensRoom": wardensRoom(); break;
            case "suicide": suicide(); break;
            case "alienCell": alienCell(); break;
            case "help": help(); break;
            case "secretPath": secretPath(); break;
            case "controlPanel": controlPanel(); break;
            case "unlockAlienCell": unlockAlienCell(); break;
            case "unlockWeaponLocker": unlockWeaponLocker(); break;
            case "unlockAmeliaCell": unlockAmeliaCell(); break;
            case "assaultRifle": assaultRifle(); break;
            case "ameliaCell": ameliaCell(); break;
            case "who": who(); break;
            case "heisenberg": heisenberg(); break;
            case "honestReply": honestReply(); break;
            case "familiar": familiar(); break;
            case "ensure": ensure(); break;
            case "escapeChoice": escapeChoice(); break;
            case "escapeChoice2": escapeChoice2(); break;
            case "room1": room1(); break;
            case "quiz": quiz(); break;
            case "incorrect": incorrect(); break;
            case "correct": correct(); break;
            case "room2": room2(); break;
            case "trueEnd": trueEnd(); break;
            case "hangar": hangar(); break;
            case "gunfight": gunfight(); break;
            case "silentTakeDown": silentTakeDown(); break;
            case "starship": starship(); break;
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

            if(gun == false){
                nextPos1 = "killed";
            }else{
                nextPos1 = "keycard";
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
        alienDead = true;

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
            gs.tv_game_content.setText("You picked up Gun Part #1 and 600 credits." +
                    "\n\nThe amount of credits you currently own: " + player.getMoney());

            if(isGunPart3Bought && storageEntered && isWeaponLockerUnlocked){
                finishedAtWhichPart = 1;
                gs.btn1.setText("Craft new gun");
                nextPos1 = "assaultRifle";
            }else{
                gs.btn1.setText("Go back");
                nextPos1 = "secondFloor";
            }
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");
            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);
        }else{
            gs.img.setImageResource(R.drawable.openchest);
            gs.tv_game_content.setText("There is nothing left in this room. Why did you come back here?");
            gs.btn1.setText("Go back");
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");
            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);
            nextPos1 = "secondFloor";
        }
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    int finishedAtWhichPart = 0;

    //switch to this screen when player found all 3 gun parts
    //at 3 different locations namely storage room, weapon locker and vending machine
    public void assaultRifle(){
        gs.img.setImageResource(R.drawable.assaultrifle);
        newGunAcquired = true;
        gs.tv_game_content.setText("You finally acquired all 3 gun parts and assembled an assault rifle. " +
                "Now you truly are a force to be reckoned with.");

        gs.btn1.setText("Nice");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        if(finishedAtWhichPart == 3) nextPos1 = "shop";
        if(finishedAtWhichPart == 2) nextPos1 = "controlPanel";
        if(finishedAtWhichPart == 1) nextPos1 = "secondFloor";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void prison() {
        gs.img.setImageResource(R.drawable.airtighthatch);

        gs.tv_game_content.setText("You are at the prison. You wonder what kind of surprises " +
                "awaits." +
                "\n\nWhere will you go?");

        gs.btn1.setText("Wardens' room");
        gs.btn2.setText("Cell #1");
        gs.btn3.setText("Cell #2");
        gs.btn4.setText("Go back");

        showALlButtons();

        nextPos1 = "wardensRoom";
        nextPos2 = "alienCell";
        nextPos3 = "ameliaCell";
        nextPos4 = "secondFloor";
    }

    boolean isQuestGiven = false;
    boolean isWardensCleared = false;
    public void wardensRoom(){
        if(!isWardensCleared){
            gs.img.setImageResource(R.drawable.wardenroom);

            gs.tv_game_content.setText("This is the wardens' room. There are alien guards inside." +
                    "\n\nWhat will you do?");

            gs.btn1.setText("Kill all");
            gs.btn2.setText("Use the secret path");
            gs.btn3.setText("Go back for now");
            gs.btn4.setText("");

            showALlButtons();
            gs.btn4.setVisibility(View.INVISIBLE);
            if(!isQuestGiven){
                gs.btn2.setVisibility(View.INVISIBLE);
            }

            nextPos1 = "suicide";
            nextPos2 = "secretPath";
            nextPos3 = "secondFloor";
        }else{
            gs.img.setImageResource(R.drawable.wardenroom);

            gs.tv_game_content.setText("You entered the wardens' room. The alien guards lie lifeless on the floor." +
                    "\n\nWhat will you do?");

            gs.btn1.setText("Access the control panel");
            gs.btn2.setText("Go back for now");
            gs.btn3.setText("");
            gs.btn4.setText("");

            showALlButtons();
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            nextPos1 = "controlPanel";
            nextPos2 = "secondFloor";
            nextPos3 = "";
        }
        nextPos4 = "";

    }

    public void secretPath(){
        gs.img.setImageResource(R.drawable.secretpath);

        gs.tv_game_content.setText("You tried to use the secret path the alien told you about. " +
                "Seemed like it wasn't lying. You infiltrated the room and quietly eliminated each and every warden alien. " +
                "Geez, what a serial killer you are. Is killing the only option?");
        isWardensCleared = true;
        gs.btn1.setText("Shut up. This is just a game.");
        gs.btn2.setText("I don't want to, it can't be helped");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "controlPanel";
        nextPos2 = "controlPanel";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void controlPanel(){
        gs.img.setImageResource(R.drawable.security);

        gs.tv_game_content.setText("You accessed the monitor area. The prison control panel is now in your hand. What shall you do");
        gs.btn1.setText("Unlock the alien's cell (cell #1)");
        if(isLabCleared) gs.btn2.setText("Unlock Amelia's cell");
        else gs.btn2.setText("Unlock cell #2");
        gs.btn3.setText("Unlock the weapon locker in the room");
        gs.btn4.setText("Go back");

        showALlButtons();
        if(isReleased) gs.btn1.setVisibility(View.INVISIBLE);
        if(isAmeliaCellUnlocked) gs.btn2.setVisibility(View.INVISIBLE);
        if(isWeaponLockerUnlocked) gs.btn3.setVisibility(View.INVISIBLE);

        nextPos1 = "unlockAlienCell";
        nextPos2 = "unlockAmeliaCell";
        nextPos3 = "unlockWeaponLocker";
        nextPos4 = "prison";
    }

    public void unlockAlienCell(){
        gs.img.setImageResource(R.drawable.unlocked);

        gs.tv_game_content.setText("You unlocked the alien cell");
        gs.btn1.setText("Keep using control panel");
        gs.btn2.setText("Go back");
        gs.btn3.setText("");
        gs.btn4.setText("");
        isReleased = true;
        showALlButtons();

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "controlPanel";
        nextPos2 = "prison";
        nextPos3 = "";
        nextPos4 = "";
    }

    boolean isWeaponLockerUnlocked = false;
    public void unlockWeaponLocker(){
        gs.img.setImageResource(R.drawable.brokenassaultrifle);
        player.setMoney(player.getMoney() + 500);
        gs.tv_game_content.setText("You unlocked the weapon locker in the room. You acquired Gun Part #2 and 500 credits." +
                "\n\nThe amount of credits you currently own: " + player.getMoney());
        isWeaponLockerUnlocked = true;

        showALlButtons();
        if(isGunPart3Bought && storageEntered && isWeaponLockerUnlocked){
            finishedAtWhichPart = 2;
            gs.btn1.setText("Craft new weapon");
            gs.btn2.setText("");
            gs.btn2.setVisibility(View.INVISIBLE);
            nextPos1 = "assaultRifle";
            nextPos2 = "";
        }else{
            gs.btn1.setText("Keep using control panel");
            gs.btn2.setText("Go back");
            nextPos1 = "controlPanel";
            nextPos2 = "prison";
        }

        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos3 = "";
        nextPos4 = "";
    }

    boolean isAmeliaCellUnlocked = false;

    public void unlockAmeliaCell(){
        gs.img.setImageResource(R.drawable.unlocked);

        if(isLabCleared) gs.tv_game_content.setText("You unlocked Amelia's cell");
        else gs.tv_game_content.setText("You unlocked cell #2");
        gs.btn1.setText("Keep using control panel");
        gs.btn2.setText("Go back");
        gs.btn3.setText("");
        gs.btn4.setText("");
        isAmeliaCellUnlocked = true;
        showALlButtons();

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "controlPanel";
        nextPos2 = "prison";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void suicide(){
        gs.img.setImageResource(R.drawable.brokenskull);

        gs.tv_game_content.setText("You tried to go in rambo styled, but the aliens are very well equipped " +
                "(of course wardens have good gears, duh?). You quickly got outgunned and killed in action." +
                "\nEnding #5: This One Simply Never Learns");

        gs.btn1.setText("Retry");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "wardensRoom";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    boolean isReleased = false;
    boolean isQuestCompleted = false;

    public void alienCell(){
        if(!isQuestCompleted){
            if(!isReleased){
                if(!isQuestGiven){
                    gs.img.setImageResource(R.drawable.prisoner);

                    gs.tv_game_content.setText("This cell holds an Alien prisoner. The alien looks tired. " +
                            "It looks at you, as if to beg for help." +
                            "\n\nWhat would you do?");

                    gs.btn1.setText("Help it");
                    gs.btn2.setText("Ignore for now");
                    gs.btn3.setText("");
                    gs.btn4.setText("");

                    gs.btn3.setVisibility(View.INVISIBLE);
                    gs.btn4.setVisibility(View.INVISIBLE);

                    nextPos1 = "help";
                    nextPos2 = "prison";
                }else{
                    gs.img.setImageResource(R.drawable.prisoner);

                    gs.tv_game_content.setText("The alien still waits for you to open its cell.");

                    gs.btn1.setText("Get going");
                    gs.btn2.setText("");
                    gs.btn3.setText("");
                    gs.btn4.setText("");

                    gs.btn2.setVisibility(View.INVISIBLE);
                    gs.btn3.setVisibility(View.INVISIBLE);
                    gs.btn4.setVisibility(View.INVISIBLE);

                    nextPos1 = "prison";
                    nextPos2 = "";
                }
            }else{
                gs.img.setImageResource(R.drawable.prisoner);

                gs.tv_game_content.setText("The alien thanked you again for having rescued it. " +
                        "As promised, it gave you the password to a room in the living quarter in the third floor. " +
                        "You looked at the alien as it ran away");
                isQuestCompleted = true;
                gs.btn1.setText("Keep going");
                gs.btn2.setText("");
                gs.btn3.setText("");
                gs.btn4.setText("");

                gs.btn2.setVisibility(View.INVISIBLE);
                gs.btn3.setVisibility(View.INVISIBLE);
                gs.btn4.setVisibility(View.INVISIBLE);

                nextPos1 = "prison";
                nextPos2 = "";
            }
        }else{
            gs.img.setImageResource(R.drawable.openedcell);

            gs.tv_game_content.setText("The cell is empty. You wonder where the alien you rescued has run off to.");
            gs.btn1.setText("Keep going");
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

            nextPos1 = "prison";
            nextPos2 = "";
        }
        nextPos3 = "";
        nextPos4 = "";

    }

    public void help(){
        if(isFoodBought){
            gs.img.setImageResource(R.drawable.give);

            gs.tv_game_content.setText("You gave the alien prisoner the food you bought earlier. " +
                    "It thanked you and show you a secret path into the wardens' room. It also wanted " +
                    "you to release it and promise to give you another reward.");

            gs.btn1.setText("Accept favor");
            gs.btn2.setText("It doesn't hurt to help");
            gs.btn3.setText("");
            gs.btn4.setText("");
            isQuestGiven = true;

            nextPos1 = "prison";
            nextPos2 = "prison";
        }else{
            gs.img.setImageResource(R.drawable.starve);

            gs.tv_game_content.setText("You don't have anything to give it. The alien still looks tired.");

            gs.btn1.setText("Ignore for now");
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");

            gs.btn2.setVisibility(View.INVISIBLE);

            nextPos1 = "prison";
            nextPos2 = "";
        }
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos3 = "";
        nextPos4 = "";
    }

    boolean isHangarKeycardAcquired = false;
    public void ameliaCell(){
        if(isLabCleared){
            if(isAmeliaCellUnlocked){
                if(isHangarKeycardAcquired){
                    gs.img.setImageResource(R.drawable.openedcell);

                    gs.tv_game_content.setText("You entered Amelia's cell. There is nothing else to do here.");
                }else{
                    gs.img.setImageResource(R.drawable.keycard);

                    gs.tv_game_content.setText("You entered Amelia's cell. Just as she told you, a keycard to the hangar " +
                            "was carefully hidden behind a small crack in the wall. You took it and moved on.");
                    isHangarKeycardAcquired = true;
                }
            }else{
                gs.img.setImageResource(R.drawable.openedcell);

                gs.tv_game_content.setText("This is Amelia's cell. You need to unlock it to get the keycard to the hangar she hid inside.");

            }
        }else{
            if(isAmeliaCellUnlocked){
                if(isHangarKeycardAcquired){
                    gs.img.setImageResource(R.drawable.openedcell);

                    gs.tv_game_content.setText("You entered the cell. There is nothing else to do here.");
                }else{
                    gs.img.setImageResource(R.drawable.keycard);

                    gs.tv_game_content.setText("You entered the cell. There is no one here. However, you spotted a keycard " +
                            "to the hangar carefully hidden behind a small crack in the wall. You took it and moved on.");
                    isHangarKeycardAcquired = true;
                }
            }else {
                gs.img.setImageResource(R.drawable.openedcell);

                gs.tv_game_content.setText("This is someone's cell. There is nothing extraordinary about it.");

            }
        }
        gs.btn1.setText("Go back");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");
        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos1 = "prison";
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
            showALlButtons();
            gs.btn4.setVisibility(View.INVISIBLE);
            nextPos1 = "gunBlazing";
            nextPos2 = "sneakLabs";
            nextPos3 = "secondFloor";
        }else{
            gs.img.setImageResource(R.drawable.chemicaltank);
            gs.tv_game_content.setText("You entered the labs. There are just corpses of dead aliens lying around. " +
                    "They won't be experimenting on anything anytime soon - you thought.");

            gs.btn1.setText("Go back");
            gs.btn2.setText("");
            gs.btn3.setText("");
            gs.btn4.setText("");
            showALlButtons();
            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);
            nextPos1 = "secondFloor";
            nextPos2 = "";
            nextPos3 = "";
        }
        nextPos4 = "";

    }

    boolean newGunAcquired = false;
    public void gunBlazing() {

        if(newGunAcquired == true){
            gs.img.setImageResource(R.drawable.takedown);
            gs.tv_game_content.setText("You tried to go in gun-blazing. With your newly acquired gun, the aliens" +
                    " are no match for you. You defeated them and successfully rescued the human captive.");
            isLabCleared = true;
            gs.btn1.setText("Talk to the captive");

            nextPos1 = "rescueSuccessful";
        }else{
            gs.img.setImageResource(R.drawable.brokenskull);
            gs.tv_game_content.setText("You tried to go in gun-blazing, but you are undergeared " +
                    "and quickly got outgunned by the alien. You died. GAME OVER" +
                    "\n\n Ending #3: History often repeats itself.");

            gs.btn1.setText("Try Again");

            nextPos1 = "labs";
        }
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";

    }

    public void sneakLabs() {
        if(isSuitBought == false){
            gs.img.setImageResource(R.drawable.brokenskull);
            gs.tv_game_content.setText("You tried to sneak your way in, but you are not a ninja. Everyone can see you. " +
                    "You quickly got detected and killed. Maybe next time bring something like Harry Potter's invisible cloak" +
                    ", it may change the outcome of this decision. You died. GAME OVER" +
                    "\n Ending #4: The Emperor's New Clothes.");

            gs.btn1.setText("Try Again");

            nextPos1 = "labs";
        }else{
            gs.img.setImageResource(R.drawable.stealthsuit);
            gs.tv_game_content.setText("You tried to sneak your way in. With your stealth suit, no one could detect your presence." +
                    " you quickly assassinated the aliens one by one and successfully rescued the human captive.");
            isLabCleared = true;
            gs.btn1.setText("Talk to the captive");

            nextPos1 = "rescueSuccessful";
        }
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";

    }


    public void rescueSuccessful(){
        gs.img.setImageResource(R.drawable.talk);

        gs.tv_game_content.setText("The captive is a female human, who was also captured by the aliens. " +
                "She was about to be dissected when you intervened.\n\n" +
                "Captive: Thank you so much for rescuing me from those awful creatures");

        gs.btn1.setText("- Who are you?");
        gs.btn2.setText("- Are you OK?");
        gs.btn3.setText("- Any ideas how to escape?");
        gs.btn4.setText("- No more talking, let's go.");

        showALlButtons();

        nextPos1 = "who";
        nextPos2 = "ensure";
        nextPos3 = "escapeChoice";
        nextPos4 = "secondFloor";
    }

    public void who(){
        gs.img.setImageResource(R.drawable.pilot);

        gs.tv_game_content.setText("My name is Amelia. I'm a pilot back on Earth. I got kidnapped by " +
                "these Aliens while I was on trying to fly across the world. And you are?");

        gs.btn1.setText("- I'm Heisenberg. Now say my name.");
        gs.btn2.setText("- I was brought onto this ship by the aliens as well.");
        gs.btn3.setText("- Wait a minute... I think I recognize you somewhere");
        gs.btn4.setText("");

        showALlButtons();
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "heisenberg";
        nextPos2 = "honestReply";
        nextPos3 = "familiar";
        nextPos4 = "";
    }

    public void heisenberg(){
        gs.img.setImageResource(R.drawable.heisenberg);

        gs.tv_game_content.setText("Heisenberg?");

        gs.btn1.setText("- You're goddamn right.");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "rescueSuccessful";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void honestReply(){
        gs.img.setImageResource(R.drawable.pilot);

        gs.tv_game_content.setText("I still don't catch your name. Hmm, doesn't matter. I'll call you Shepard.");

        gs.btn1.setText("- Sure, I guess..");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "rescueSuccessful";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void familiar(){
        gs.img.setImageResource(R.drawable.pilot);

        gs.tv_game_content.setText("Well, I'm not surprised. I'd say I am quite famous back on Earth." +
                " But I'm sure as hell I don't recognize you.");

        gs.btn1.setText("...");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "rescueSuccessful";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void ensure(){
        gs.img.setImageResource(R.drawable.ok);

        gs.tv_game_content.setText("Yes, I'm fine, thanks to you. But my time on this god-forsaken " +
                "place has not been very pleasant.");

        gs.btn1.setText("- Glad you're not hurt.");
        gs.btn2.setText("- Must be tough");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "rescueSuccessful";
        nextPos2 = "rescueSuccessful";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void escapeChoice(){
        gs.img.setImageResource(R.drawable.hangar);

        gs.tv_game_content.setText("Glad you asked. You can hijack a starship in the hangar, but you will need a keycard. " +
                "Lucky for you, I managed to hid one in my cell, so we will need to go there and get it. Also, you will " +
                "need me since you probably cannot fly an aircraft.");

        gs.btn1.setText("- Is that all?");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "escapeChoice2";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void escapeChoice2(){
        gs.img.setImageResource(R.drawable.commsroom);

        gs.tv_game_content.setText("Another way is to reach the comms room on this ship and call for help from Earth. " +
                "But I heard there is a big bad enemy waiting for us there. Better prepare ourselves if we decide to " +
                "cross its path.");

        gs.btn1.setText("- Alright");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "rescueSuccessful";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
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

    public void room1() {
        gs.img.setImageResource(R.drawable.computer);

        gs.tv_game_content.setText("There is a computer in the room. A note is displayed on the screen: \"do not forget " +
                "passcode to the Comms Room. Run the quiz app to remind yourself of the passcode.\"");

        gs.btn1.setText("Run the quiz app");
        gs.btn2.setText("Hell naw I'm not doing this");
        gs.btn3.setText("");
        gs.btn4.setText("");

        showALlButtons();
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "quiz";
        nextPos2 = "livingQuarter";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void quiz() {
        gs.img.setImageResource(R.drawable.quiz);

        gs.tv_game_content.setText("The app presents you with a question: Why haven't aliens visited earth?");

        gs.btn1.setText("Because the government hides alien encounters from the public.");
        gs.btn2.setText("The Fermi paradox is the discrepancy between the lack of conclusive evidence " +
                "of advanced extraterrestrial life and the apparently high likelihood of its existence.");
        gs.btn3.setText("How should I know????");
        gs.btn4.setText("Because it's rated 1 star");

        showALlButtons();

        nextPos1 = "incorrect";
        nextPos2 = "incorrect";
        nextPos3 = "incorrect";
        nextPos4 = "correct";
    }

    public void incorrect() {
        gs.img.setImageResource(R.drawable.cross);

        gs.tv_game_content.setText("Incorrect. That is not even close. Try again.");

        gs.btn1.setText("Try again");
        gs.btn2.setText("Screw this, I'm out");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "quiz";
        nextPos2 = "room1";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void correct() {
        gs.img.setImageResource(R.drawable.check);

        gs.tv_game_content.setText("\"Correct. Earth was so meh! Been there, done that!.\" - said the AI voice. " +
                "It gave you the passcode sequence afterwards:\n\nX O ◻ △");

        gs.btn1.setText("Go back");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "room1";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
    }

    public void room2() {
        showALlButtons();
        if(!isQuestCompleted){
            gs.img.setImageResource(R.drawable.smartlock);

            gs.tv_game_content.setText("The room is locked. Seems like you need a passcode to open this door.");

            gs.btn1.setText("Leave it for now");
            gs.btn2.setText("");

            gs.btn2.setVisibility(View.INVISIBLE);

            nextPos1 = "livingQuarter";
            nextPos2 = "";
        }else{
            gs.img.setImageResource(R.drawable.bunkbeds);

            gs.tv_game_content.setText("There is not much here. Just another room in the living quarter. Yet that bed you see " +
                    "is pretty cozy. You are quite tired of your adventure so far and your body tells you to take a little nap." +
                    "\nWhat shall you do?");

            gs.btn1.setText("Take a nap");
            gs.btn2.setText("Leave the room");

            nextPos1 = "trueEnd";
            nextPos2 = "livingQuarter";
        }
        gs.btn3.setText("");
        gs.btn4.setText("");
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos3 = "";
        nextPos4 = "";

    }

    public void trueEnd() {
        gs.img.setImageResource(R.drawable.wakeup);

        gs.tv_game_content.setText("After a while, you wake up. You suddenly realize this isn't the " +
                "bed on the alien ship, but at home. Your brother comes in. You explain the " +
                "situation to him. But he assure you this is the year 2007, nothing like an aliens invasion " +
                "ever happened and that you had just been having a bad dream. TRUE ENDING: It's all just a dream!");

        gs.btn1.setText("Try another ending");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);

        nextPos1 = "room2";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";
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

            showALlButtons();
            if(isGunPart3Bought && storageEntered && isWeaponLockerUnlocked){
                finishedAtWhichPart = 3;
                gs.btn1.setText("Craft new gun");
                gs.btn2.setText("");
                gs.btn2.setVisibility(View.INVISIBLE);
                nextPos1 = "assaultRifle";
                nextPos2 = "";
            }else{
                gs.btn1.setText("Keep buying");
                gs.btn2.setText("Stop buying");
                nextPos1 = "shop";
                nextPos2 = "thirdFloor";
            }

            gs.btn3.setText("");
            gs.btn4.setText("");
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);

        }else{
            gs.img.setImageResource(R.drawable.nomoney);
            gs.tv_game_content.setText("You do not have enough credits.\nNeed: 500 credits" +
                    "\nYou have: " + player.getMoney() + " credits");

            gs.btn1.setText("Buy something else");
            gs.btn1.setVisibility(View.VISIBLE);
            showALlButtons();
            gs.btn2.setText("Stop buying");
            gs.btn3.setText("");
            gs.btn4.setText("");
            gs.btn3.setVisibility(View.INVISIBLE);
            gs.btn4.setVisibility(View.INVISIBLE);
            nextPos1 = "shop";
            nextPos2 = "thirdFloor";

        }
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
        showALlButtons();
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
        showALlButtons();
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

    public void hangar() {
        showALlButtons();
        if(isHangarKeycardAcquired){
            gs.img.setImageResource(R.drawable.hangar);

            gs.tv_game_content.setText("You entered the hangar. All that's left to do is to hijack a starship and" +
                    "get back to Earth. However, there are too many aliens here. What's the plan?");

            gs.btn1.setText("Gun them all down");
            gs.btn2.setText("Take them down silently");
            gs.btn3.setText("Wait, I'm not ready yet");

            nextPos1 = "gunfight";
            nextPos2 = "silentTakeDown";
            nextPos3 = "thirdFloor";
        }else{
            gs.img.setImageResource(R.drawable.keycardlock);

            gs.tv_game_content.setText("The gate is locked. You need some sort of keycard to swipe it open.");

            gs.btn1.setText("Go back for now");
            gs.btn2.setText("");
            gs.btn3.setText("");

            gs.btn2.setVisibility(View.INVISIBLE);
            gs.btn3.setVisibility(View.INVISIBLE);

            nextPos1 = "thirdFloor";
            nextPos2 = "";
            nextPos3 = "";
        }
        gs.btn4.setText("");
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos4 = "";
    }


    public void gunfight() {
        if(newGunAcquired){
            gs.img.setImageResource(R.drawable.shooting);
            gs.tv_game_content.setText("You started a fight with the aliens. With the power of your new rifle and your " +
                    "experience thus far, you finally managed to clear a path to a starship.");

            gs.btn1.setText("Board the ship");

            nextPos1 = "starship";
        }else{
            gs.img.setImageResource(R.drawable.brokenskull);
            gs.tv_game_content.setText("You started a fight with the aliens. Not sure what you were thinking, because " +
                    "there are simply too many of them. The aliens' firepower quickly overwhelms you and you died. " +
                    "GAME OVER. Ending #6: Underpowered");

            gs.btn1.setText("Try again");

            nextPos1 = "hangar";
        }
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";

    }

    public void silentTakeDown() {
        if(isSuitBought){
            gs.img.setImageResource(R.drawable.stealthsuit);
            gs.tv_game_content.setText("You decided to sneak your way through to the starship. With the stealth suit, you " +
                    "successfully infiltrated the area and took down guards as they got in your way.");

            gs.btn1.setText("Board the ship");

            nextPos1 = "starship";
        }else{
            gs.img.setImageResource(R.drawable.brokenskull);
            gs.tv_game_content.setText("You decided to sneak your way through to the starship. Not sure what you were thinking, because " +
                    "there are simply too many of them. The aliens quickly detected and killed you. You died. " +
                    "GAME OVER. Ending #8: Mission Impossible But You're Not Ethan Hunt");

            gs.btn1.setText("Try again");

            nextPos1 = "hangar";
        }
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";

    }

    public void starship() {
        if(isLabCleared){
            gs.img.setImageResource(R.drawable.counterearth);
            gs.tv_game_content.setText("You finally made it onto the starship with Amelia. She quickly started the engines " +
                    "and flew you both back to Earth. However, this \"Earth\" wasn't the same Earth you knew. Everything was inverted, " +
                    "as if this were a mirrored world. Looks like you have yet to make it back.\nEnding #7: Counter-Earth");

        }else{
            gs.img.setImageResource(R.drawable.controlroom);
            gs.tv_game_content.setText("You finally made it onto the starship. However, you hadn't thought one thing through." +
                    " You do not know how to start and fly a starship. You took too long to learn the " +
                    "controls and the aliens finally caught up to you. GAME OVER. Ending #9: I Believe I Can Fly");

        }
        gs.btn1.setText("Try another ending");
        gs.btn2.setText("");
        gs.btn3.setText("");
        gs.btn4.setText("");

        gs.btn2.setVisibility(View.INVISIBLE);
        gs.btn3.setVisibility(View.INVISIBLE);
        gs.btn4.setVisibility(View.INVISIBLE);
        nextPos1 = "hangar";
        nextPos2 = "";
        nextPos3 = "";
        nextPos4 = "";

    }

}

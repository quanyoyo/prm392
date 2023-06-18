package com.example.prm392;

import android.view.View;

public class Story {

    GameScreen gs;
    String nextPos1, nextPos2, nextPos3, nextPos4;

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
        }
    }

    public void showALlButtons(){
        gs.btn1.setVisibility(View.VISIBLE);
        gs.btn2.setVisibility(View.VISIBLE);
        gs.btn3.setVisibility(View.VISIBLE);
        gs.btn4.setVisibility(View.VISIBLE);
    }


    public void startingPoint(){
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

    }

    public void weapon(){

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
        nextPos3 = "";
        nextPos4 = "";

    }

    public void openChest(){
        gs.image.setImageResource(R.drawable.raygun);

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

    public void stay(){

        gs.image.setImageResource(R.drawable.disintegrate);

        gs.text.setText("You think about this for a moment, and then you remembered you actually got kidnapped by aliens, " +
                "and now you're on their ship. Suddenly, an alien comes into the room to find you and bring you to the lab. " +
                "They do experiments on you and you die from the trauma and pain. GAME OVER");

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

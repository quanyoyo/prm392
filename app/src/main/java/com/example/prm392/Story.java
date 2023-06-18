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
            case "stay": stay(); break;
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

    public void stay(){

        gs.image.setImageResource(R.drawable.disintegrate);

        gs.text.setText("You think about this for a moment, and then you remembered you actually got kidnapped by Aliens," +
                "\nand now ur on their ship. Suddenly, an alien comes into the room to find you and bring you to the lab." +
                "\nThey do experiments on you and you die from the trauma and pain.");

        gs.btn1.setText("Retry");
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
}

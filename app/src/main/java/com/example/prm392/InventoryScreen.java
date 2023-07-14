package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.List;

public class InventoryScreen extends AppCompatActivity {
    private GridView inventoryGrid;
    private InventoryAdaptor inventoryAdaptor;
    private ImageButton btnBack;
    MediaPlayer mediaPlayer;

    private List<Item> itemList;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_screen);

        mediaPlayer = MediaPlayerSingleton.getInstance(this);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // Retrieve the GridView
        inventoryGrid = findViewById(R.id.inventory_grid);

        // Retrieve the items from the database
        database = Story.getDatabase();
        retrieveItemsFromDatabase();

        btnBack = ((ImageButton) findViewById(R.id.btn_back));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGameScreen();
            }
        });
    }
    private void retrieveItemsFromDatabase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Access the item DAO and retrieve all items
                itemList = database.itemDao().getAll();

                // Create a new item for the player's money
                int playerMoney = Story.getPlayerMoney(); // Replace this with your own method to get the player's money
                Item playerMoneyItem = new Item(""+playerMoney, R.drawable.money);
                itemList.add(0, playerMoneyItem); // Add it at the beginning of the item list
                // Run the UI updates on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Create and set the adapter
                        inventoryAdaptor = new InventoryAdaptor(InventoryScreen.this, itemList);
                        inventoryGrid.setAdapter(inventoryAdaptor);
                    }
                });
            }
        }).start();
    }
    public void goToGameScreen() {
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerSingleton.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaPlayerSingleton.resume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources
        MediaPlayerSingleton.release();
    }
}
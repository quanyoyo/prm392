package com.example.prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class InventoryScreen extends AppCompatActivity {
    private GridView inventoryGrid;
    private InventoryAdaptor inventoryAdaptor;
    private List<Item> itemList;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setContentView(R.layout.activity_inventory_screen);

        // Retrieve the GridView
        inventoryGrid = findViewById(R.id.inventory_grid);

        // Prepare item data
        itemList = new ArrayList<>();
        itemList.add(new Item("Item 1", R.drawable.keycard));
        itemList.add(new Item("Item 2", R.drawable.raygun));
        itemList.add(new Item("Item 3", R.drawable.brokenassaultrifle));
        // Add more items as needed

        // Create and set the adapter
        inventoryAdaptor = new InventoryAdaptor(itemList, this);
        inventoryGrid.setAdapter(inventoryAdaptor);

        btnBack = ((ImageButton) findViewById(R.id.btn_back));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGameScreen();
            }
        });
    }
    public void goToGameScreen() {
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }
}
package com.example.prm392;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameRecordScreen extends AppCompatActivity {
    private List<GameRecord> gameRecordList = new ArrayList<>();
    private GameRecordAdapter gameRecordAdapter;
    private RecyclerView gameRecordRecyclerView;
    private Button btnDelete;
    private Database database;
    private int totalRecords = 0;

    private TextView tv_total;
    private ImageButton btnBack;
    private MediaPlayerSingleton mediaPlayerSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_record_screen);

        gameRecordRecyclerView = findViewById(R.id.recyclerview_records);
        // Set the layout manager for the RecyclerView
        gameRecordRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create or retrieve the database instance
        database = MainActivity.getDatabase();
        // Retrieve the game records from the database
        retrieveGameRecordsFromDatabase();
        tv_total = ((TextView) findViewById(R.id.tv_total));


//        gameRecordList.clear();
//        gameRecordList.addAll(database.gameRecordDao().getAllGameRecords());
//        gameRecordAdapter.notifyDataSetChanged();
//        Log.d("record", gameRecordList.toString());

        mediaPlayerSingleton = MediaPlayerSingleton.getInstance(this);
        mediaPlayerSingleton.resume();

        btnBack = ((ImageButton) findViewById(R.id.btn_back));
        btnDelete = ((Button) findViewById(R.id.btn_delete));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeletePopupDialog();
            }
        });
    }

    private void retrieveGameRecordsFromDatabase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Access the game record DAO and retrieve all game records
                gameRecordList = database.gameRecordDao().getAllGameRecords();
                totalRecords = gameRecordList.size();
                tv_total.setText(totalRecords+"/12");
                // Run the UI updates on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Create and set the adapter
                        gameRecordAdapter = new GameRecordAdapter(gameRecordList);
                        gameRecordRecyclerView.setAdapter(gameRecordAdapter);
                    }
                });
            }
        }).start();
    }
    private void showDeletePopupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all your records");
        builder.setMessage("Are you sure");

        // Add any additional customization to the dialog here, such as buttons, etc.

        // Set a click listener for the "Resume" button (optional)
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle yes functionality
                database.gameRecordDao().deleteAllGameRecords();
                goToMain();
            }
        });

        // Set a click listener for the "Quit" button (optional)
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle no functionality
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerSingleton.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources
        MediaPlayerSingleton.release();
    }
}
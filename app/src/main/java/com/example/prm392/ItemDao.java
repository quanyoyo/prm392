package com.example.prm392;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Item item);

    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Delete
    void deleteItem(Item item);

    @Query("SELECT * FROM item WHERE name = :name")
    Item getItemById(String name);

    @Query("DELETE FROM item")
    void deleteAllItems();
}

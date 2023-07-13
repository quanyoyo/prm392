package com.example.prm392;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGameRecord(GameRecord gameRecord);

    @Query("SELECT * FROM game_records")
    List<GameRecord> getAllGameRecords();

    @Query("DELETE FROM game_records")
    void deleteAllGameRecords();
}

package com.example.prm392;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Item.class, GameRecord.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract ItemDao itemDao();
    public abstract GameRecordDao gameRecordDao();
}

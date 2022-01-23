package com.example.pool.database;

import androidx.room.RoomDatabase;

import com.example.pool.models.GameState;

@androidx.room.Database(entities = {GameState.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract GameStateDao getGameStateDao();
}

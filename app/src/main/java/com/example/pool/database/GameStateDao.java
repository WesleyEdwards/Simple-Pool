package com.example.pool.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pool.models.GameState;

import org.intellij.lang.annotations.JdkConstants;

import java.util.List;
//CRUD
//create
//read
//update
//delete

@Dao
public interface GameStateDao {

    @Insert
    public long insert(GameState gameState);

    @Query("SELECT * FROM gamestate")
    public List<GameState> getAll();


    @Query("SELECT * FROM gamestate WHERE id = :id LIMIT 1")
    public GameState findById(long id);

    @Update
    public void update(GameState gameState);

    @Delete
    public void delete(GameState gameState);

}

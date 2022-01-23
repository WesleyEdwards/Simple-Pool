package com.example.pool.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pool.Ball;

import java.util.ArrayList;

@Entity
public class GameState {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "created_at")
    public long createdAt;


//    Right now it's just saving the number of balls.
//    @ColumnInfo
//    public int balls;

    @ColumnInfo
    public int highScoreData;


}

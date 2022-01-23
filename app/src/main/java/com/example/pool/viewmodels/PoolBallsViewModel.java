package com.example.pool.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pool.Ball;
import com.example.pool.DrawingView;
import com.example.pool.R;
import com.example.pool.Table;
import com.example.pool.models.GameState;
import com.example.pool.database.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PoolBallsViewModel extends AndroidViewModel {
    private Database database;
    MutableLiveData<Boolean> saving = new MutableLiveData<>();
    ObservableArrayList<GameState> gameStates = new ObservableArrayList<>();
    int currScore = 1;
    public PoolBallsViewModel(@NonNull Application application) {
        super(application);

        saving.setValue(false);
        database = Room.databaseBuilder(application, Database.class, "tableinfo").build();

//        new Thread(() -> {
//            List<GameState> score = database.getGameStateDao().getAll();
//            currScore = score.get(0).highScoreData;
//        }).start();

    }

    public MutableLiveData<Boolean> getSaving() {
        return saving;
    }

    public int getScore() {
        return currScore;
    }

    public void saveGameState(Table table) {
//        I just commented this out. and it changed stuff I guess.
//        saving.setValue(true);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GameState gameState = new GameState();
            gameState.highScoreData = table.getHighScore();
            gameState.createdAt = System.currentTimeMillis();
    //        insert into database
            gameState.id = database.getGameStateDao().insert(gameState);
            saving.postValue(false);
        }).start();

    }
}

package com.example.pool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.pool.viewmodels.PoolBallsViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//      This is in the fragment already. But it's a singleton (I think) class so you can do that.
        PoolBallsViewModel viewModel = new ViewModelProvider(this)
                .get(PoolBallsViewModel.class);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, MenuFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }
}
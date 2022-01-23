package com.example.pool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pool.viewmodels.PoolBallsViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class TableFragment extends Fragment {

    PoolBallsViewModel viewModel;
    private boolean previousSaving = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);

        Button b = (Button) view.findViewById(R.id.menuButton);
        b.setOnClickListener(view1 -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, MenuFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PoolBallsViewModel viewModel = new ViewModelProvider(getActivity()).get(PoolBallsViewModel.class);

//        viewModel

        viewModel.getSaving().observe(getViewLifecycleOwner(), (saving) -> {
            if (saving && !previousSaving) {
                MaterialButton button = view.findViewById(R.id.saveButton);
                button.setEnabled(false);
//                this here........
//                button.setText("Saving...");
                previousSaving = saving;
            } else if (previousSaving && !saving) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button save = (Button) view.findViewById(R.id.saveButton);
        save.setOnClickListener(view1 -> {

            DrawingView drawingView = view.findViewById(R.id.drawingView);
            viewModel.saveGameState(drawingView.table);

            TextView textView = view.findViewById(R.id.scoreText);

            if (drawingView.table.getScore() > drawingView.table.highScore) {
                textView.setText("High Score " + drawingView.table.getScore());
//                drawingView.table.highScore = drawingView.table.score;
                drawingView.table.highScore = viewModel.getScore();


            }
        });
    }
}
package com.example.rxjava.listchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        initData();
        initList();
    }

    private void initList() {
        listAdapter = new ListAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setHasFixedSize(true);
        listAdapter.setTasks(tasks);

        listAdapter.setOnButtonClickListener(new ListAdapter.OnButtonClickListener() {
            @Override
            public void onTravelButtonClick() {
                recyclerView.setAdapter(listAdapter);
                Log.e("MainActivity", "Travel Button");
            }
        });

    }

    private void initData() {
        tasks.add(new Task(1, "One"));
        tasks.add(new Task(2, "Two"));
        tasks.add(new Task(3, "Three"));
        tasks.add(new Task(4, "Four"));
        tasks.add(new Task(5, "Five"));
        tasks.add(new Task(6, "Six"));
        tasks.add(new Task(7, "Seven"));
        tasks.add(new Task(8, "Eight"));
        tasks.add(new Task(9, "Nine"));
        tasks.add(new Task(10, "Ten"));
        tasks.add(new Task(11, "Eleven"));
        tasks.add(new Task(12, "Twelve"));
        tasks.add(new Task(13, "Thirteen"));
        tasks.add(new Task(14, "Fourteen"));
        tasks.add(new Task(15, "Fifteen"));
        tasks.add(new Task(16, "Sixteen"));
        tasks.add(new Task(17, "Seventeen"));
        tasks.add(new Task(18, "Eighteen"));
        tasks.add(new Task(19, "Nineteen"));
        tasks.add(new Task(20, "Twenty"));
    }
}

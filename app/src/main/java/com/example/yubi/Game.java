package com.example.yubi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    TextView p1finger1 = findViewById(R.id.p1finger1);
    TextView p1finger2 = findViewById(R.id.p1finger2);
    TextView p1finger3 = findViewById(R.id.p1finger3);
    TextView p2finger1 = findViewById(R.id.p1finger1);
    TextView p2finger2 = findViewById(R.id.p1finger2);
    TextView p2finger3 = findViewById(R.id.p1finger3);
    TextView p1point = findViewById(R.id.p1point);
    TextView p2point = findViewById(R.id.p2point);
    TextView round = findViewById(R.id.round);
    TextView p1order = findViewById(R.id.p1order);
    TextView p2order = findViewById(R.id.p2order);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


    }
}
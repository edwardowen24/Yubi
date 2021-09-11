package com.example.yubi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private int mProgressStatus = 0;
    int nextRound = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /////////////// p1 = player1 ///////////////
        TextView p1finger1 = findViewById(R.id.p1finger1);
        TextView p1finger2 = findViewById(R.id.p1finger2);
        TextView p1finger3 = findViewById(R.id.p1finger3);
        /////////////// p2 = player2 ///////////////
        TextView p2finger1 = findViewById(R.id.p2finger1);
        TextView p2finger2 = findViewById(R.id.p2finger2);
        TextView p2finger3 = findViewById(R.id.p2finger3);
        TextView p1point = findViewById(R.id.p1point);
        TextView p2point = findViewById(R.id.p2point);
        TextView round = findViewById(R.id.round);
        TextView p1order = findViewById(R.id.p1order);
        TextView p2order = findViewById(R.id.p2order);
        ImageView rbtnImg = findViewById(R.id.resetIcon);
        ImageView cbtnImg = findViewById(R.id.imageCheckButton);
        ProgressBar progressBar = findViewById(R.id.progressBar1);
        ArrayList<Integer> p1ArrayList = new ArrayList<>();
        ArrayList<Integer> p2ArrayList = new ArrayList<>();

//        gameInit(round, p1ArrayList, p2ArrayList);
//        while(nextRound != p1ArrayList.size() && nextRound != p2ArrayList.size()) {


            //////////////// player 1 = blue ////////////////
            p1finger1.setOnClickListener(view -> {
                p1ArrayList.add(1);
            });
            p1finger2.setOnClickListener(view -> {
                p1ArrayList.add(2);
            });
            p1finger3.setOnClickListener(view -> {
                p1ArrayList.add(3);
            });
            //////////////// player 2 = red ////////////////
            p2finger1.setOnClickListener(view -> {
                p2ArrayList.add(1);
            });
            p2finger2.setOnClickListener(view -> {
                p2ArrayList.add(2);
            });
            p2finger3.setOnClickListener(view -> {
                p2ArrayList.add(3);
            });
//            nextRound++;
//        }
        progressBar.setMax(nextRound);

        new Thread(() -> {
            while(p1ArrayList.size() < 100){
//                mProgressStatus++;
                android.os.SystemClock.sleep(50);
                mHandler.post(() -> progressBar.setProgress(p1ArrayList.size()));
            }
        }).start();

        arrayListCheck(cbtnImg, p1ArrayList, p2ArrayList, round);
        rbtnImg.setOnClickListener(view -> gameInit(round, p1ArrayList, p2ArrayList));

    }
    public void arrayListCheck(ImageView cbtnImg, ArrayList<Integer> p1ArrayList, ArrayList<Integer> p2ArrayList, TextView round){
        cbtnImg.setOnClickListener(view -> {
            int roundInt;
            if (p1ArrayList.equals(p2ArrayList)) {
                roundInt = 2;
            } else {
                roundInt = -2;
            }
            round.setText(String.valueOf(roundInt));
        });
    }

    public void gameInit(TextView round, ArrayList<Integer> p2ArrayList, ArrayList<Integer> p1ArrayList){
        p1ArrayList.clear();
        p2ArrayList.clear();
        round.setText(String.valueOf(1));
    }
}
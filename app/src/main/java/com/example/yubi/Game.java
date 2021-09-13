package com.example.yubi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    int nextRound = 1;
    boolean orderCheck = true, isOrderCheck = true;
    int p1pointInt = 0, p2pointInt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);
        MediaPlayer wrong = MediaPlayer.create(this, R.raw.wrong);
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
        ImageView checkButtonImg = findViewById(R.id.imageCheckButton);
        TextView clickCheck = findViewById(R.id.clickCheck);
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

            progressBar(progressBar, p1ArrayList, p2ArrayList, orderCheck);

            arrayListCheck(wrong, correct, checkButtonImg, p1ArrayList, p2ArrayList, orderCheck, p1point, p2point);

            rbtnImg.setOnClickListener(view -> reset(p1ArrayList, p2ArrayList));



//        orderCheck = setOrderCheck(isOrderCheck);

    }
    ////////// if orderCheck == true => blue; if orderCheck == false => red; /////////////
    private void progressBar( ProgressBar progressBar, ArrayList<Integer> p1ArrayList, ArrayList<Integer> p2ArrayList, boolean orderCheck) {
        progressBar.setMax(nextRound);
        if(orderCheck){
            new Thread(() -> {
                while(p1ArrayList.size() < 100){
                    android.os.SystemClock.sleep(50);
                    mHandler.post(() -> progressBar.setProgress(p1ArrayList.size()));
                }
            }).start();
        }else{
            new Thread(() -> {
                while(p2ArrayList.size() < 100){
                    android.os.SystemClock.sleep(50);
                    mHandler.post(() -> progressBar.setProgress(p2ArrayList.size()));
                }
            }).start();
        }
    }

    @SuppressLint("ResourceAsColor")
    public void arrayListCheck(MediaPlayer wrong, MediaPlayer correct, ImageView checkButtonImg, ArrayList<Integer> p1ArrayList, ArrayList<Integer> p2ArrayList, boolean orderCheck, TextView p1point, TextView p2point){
        checkButtonImg.setOnClickListener(view -> {
            boolean correctMp3;
            if (p1ArrayList.equals(p2ArrayList)) {
                correctMp3 = true;
                if(orderCheck){
                    p1pointInt += 1;
                    p1point.setText(String.valueOf(p1pointInt));
                }else{
                    p2pointInt += 1;
                    p2point.setText(String.valueOf(p2pointInt));
                }
            } else {
                correctMp3 = false;
            }
            if(correctMp3){
                correct.start();
            }else{
                wrong.start();
            }
        });
    }
    public void nextRound(){

    }
    public boolean setOrderCheck(boolean isOrderCheck){
        orderCheck = isOrderCheck;
        return orderCheck;
    }
    public void point(){

    }

    public void reset(ArrayList<Integer> p2ArrayList, ArrayList<Integer> p1ArrayList){
        p1ArrayList.clear();
        p2ArrayList.clear();
    }
}
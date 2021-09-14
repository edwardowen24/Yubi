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
    int nextRound = 1, orderCheckInt = 0;
    boolean orderCheck = true;
    boolean isOrderCheck = true;
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

        orderCheck = setOrderCheck(p1order, p2order);

        progressBar(progressBar, p1ArrayList, p2ArrayList, orderCheck);

        arrayListCheck(wrong, correct, checkButtonImg, p1ArrayList, p2ArrayList, p1point, p2point, p1order, p2order);

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
    public void arrayListCheck(MediaPlayer wrong, MediaPlayer correct, ImageView checkButtonImg, ArrayList<Integer> p1ArrayList, ArrayList<Integer> p2ArrayList, TextView p1point, TextView p2point, TextView p1order, TextView p2order){

        checkButtonImg.setOnClickListener(view -> {
            boolean correctMp3;
            orderCheckInt++;
            orderCheck = setOrderCheck(p1order, p2order);
            isOrderCheck = orderCheck;
            if (p1ArrayList.equals(p2ArrayList)) {
                correctMp3 = true;
                if(!isOrderCheck){
                    p2pointInt += 1;
                }else{
                    p1pointInt += 1;
                }
            } else {
                correctMp3 = false;
                if(!isOrderCheck){
                    p1pointInt += 1;
                }else{
                    p2pointInt += 1;
                }
            }
            p1point.setText(String.valueOf(p1pointInt));
            p2point.setText(String.valueOf(p2pointInt));
            if(correctMp3){
                correct.start();
            }else{
                wrong.start();
            }
            reset(p1ArrayList, p2ArrayList);
        });
    }
    public void nextRound(){

    }
    public boolean setOrderCheck(TextView p1order,TextView p2order){
        if(orderCheckInt % 2 == 1){
            orderCheck = false;
            p2order.setVisibility(View.VISIBLE);
            p1order.setVisibility(View.INVISIBLE);
        }else{
            orderCheck = true;
            p1order.setVisibility(View.VISIBLE);
            p2order.setVisibility(View.INVISIBLE);
        }
        return orderCheck;
    }
    public void point(){

    }

    public void reset(ArrayList<Integer> p2ArrayList, ArrayList<Integer> p1ArrayList){
        p1ArrayList.clear();
        p2ArrayList.clear();
    }
}
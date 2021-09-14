package com.example.yubi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    int nextRound = 1, orderCheckInt = 0;
    boolean orderCheck = true, isOrderCheck = true;
    int p1pointInt = 0, p2pointInt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MediaPlayer correct = MediaPlayer.create(this, R.raw.correct);
        MediaPlayer wrong = MediaPlayer.create(this, R.raw.wrong);
        MediaPlayer win = MediaPlayer.create(this, R.raw.hitujigambatta);
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
        ImageView resetIconBlue = findViewById(R.id.resetIconBlue);
        ImageView resetIconRed = findViewById(R.id.resetIconRed);
        ImageView gameReset = findViewById(R.id.gameReset);
        ImageView checkButtonImg = findViewById(R.id.imageCheckButton);
        TextView winner = findViewById(R.id.winner);
        TextView p1winner = findViewById(R.id.p1winner);
        TextView p2winner = findViewById(R.id.p2winner);
        ProgressBar progressBar1 = findViewById(R.id.progressBar1);
        ProgressBar progressBar2 = findViewById(R.id.progressBar2);
        ArrayList<Integer> p1ArrayList = new ArrayList<>();
        ArrayList<Integer> p2ArrayList = new ArrayList<>();

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

        arrayListCheck(win, gameReset, winner, p1winner, p2winner, progressBar1, progressBar2, round, wrong, correct, checkButtonImg, p1ArrayList, p2ArrayList, p1point, p2point, p1order, p2order);

        resetIconBlue.setOnClickListener(view -> p1ArrayList.clear());
        resetIconRed.setOnClickListener(view -> p2ArrayList.clear());
        orderCheck(p1order, p2order);
        progressBar(progressBar1, progressBar2, p1ArrayList, p2ArrayList);
        gameReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameInit(checkButtonImg, p1order, p2order, progressBar1, progressBar2, p1winner, p2winner, winner, round, p1point, p2point, p2ArrayList, p1ArrayList);
            }
        });
        p1point.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                p1ArrayList.clear();
                return false;
            }
        });
        p2point.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                p2ArrayList.clear();
                return false;
            }
        });
    }
    ////////// if orderCheck == true => blue; if orderCheck == false => red; /////////////
    @SuppressLint("ResourceAsColor")
    public void arrayListCheck(MediaPlayer win, ImageView gameReset, TextView winner, TextView p1winner, TextView p2winner,ProgressBar progressBar1, ProgressBar progressBar2, TextView round, MediaPlayer wrong, MediaPlayer correct, ImageView checkButtonImg, ArrayList<Integer> p1ArrayList, ArrayList<Integer> p2ArrayList, TextView p1point, TextView p2point, TextView p1order, TextView p2order){
        checkButtonImg.setOnClickListener(view -> {
            boolean correctMp3;
            orderCheckInt++;
            orderCheck = orderCheck(p1order, p2order);
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
            round.setText(String.valueOf(nextRound()));
            progressBar(progressBar1, progressBar2, p1ArrayList, p2ArrayList);
            winner(win, checkButtonImg, winner, p1winner, p2winner, gameReset);
        });
    }
    public int nextRound(){
        if(orderCheckInt % 2 == 0){
            nextRound++;
        }
        return nextRound;
    }
    public boolean orderCheck(TextView p1order,TextView p2order){
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
    private void progressBar(ProgressBar progressBar1, ProgressBar progressBar2, ArrayList<Integer> p1ArrayList, ArrayList<Integer> p2ArrayList) {
        progressBar1.setMax(nextRound);
        progressBar2.setMax(nextRound);
        new Thread(() -> {
            while(p1ArrayList.size() < 100){
                android.os.SystemClock.sleep(50);
                mHandler.post(() -> progressBar1.setProgress(p1ArrayList.size()));
            }
        }).start();
        new Thread(() -> {
            while(p2ArrayList.size() < 100){
                android.os.SystemClock.sleep(50);
                mHandler.post(() -> progressBar2.setProgress(p2ArrayList.size()));
            }
        }).start();
    }
    public void point(){

    }
    public void winner(MediaPlayer win, ImageView checkButtonImg, TextView winner, TextView p1winner, TextView p2winner, ImageView gameReset){
        if(p1pointInt == 10){
            p1winner.setVisibility(View.VISIBLE);
            winner.setVisibility(View.VISIBLE);
            checkButtonImg.setEnabled(false);
            win.start();
        }
        if(p2pointInt == 10){
            p2winner.setVisibility(View.VISIBLE);
            winner.setVisibility(View.VISIBLE);
            checkButtonImg.setEnabled(false);
            win.start();
        }
    }
    public void reset(ArrayList<Integer> p2ArrayList, ArrayList<Integer> p1ArrayList){
        p1ArrayList.clear();
        p2ArrayList.clear();
    }
    public void gameInit(ImageView checkButtonImg, TextView p1order, TextView p2order, ProgressBar progressBar1, ProgressBar progressBar2, TextView p1winner, TextView p2winner, TextView winner, TextView round, TextView p2point, TextView p1point, ArrayList<Integer> p2ArrayList, ArrayList<Integer> p1ArrayList){
        p1ArrayList.clear();
        p2ArrayList.clear();
        nextRound = 1;
        p1pointInt = 0;
        p2pointInt = 0;
        orderCheckInt = 0;
        progressBar1.setMax(nextRound);
        progressBar2.setMax(nextRound);
        p1point.setText(String.valueOf(p1pointInt));
        p2point.setText(String.valueOf(p2pointInt));
        round.setText(String.valueOf(nextRound));
        winner.setVisibility(View.INVISIBLE);
        p1winner.setVisibility(View.INVISIBLE);
        p2winner.setVisibility(View.INVISIBLE);
        orderCheck = true;
        p1order.setVisibility(View.VISIBLE);
        p2order.setVisibility(View.INVISIBLE);
        checkButtonImg.setEnabled(true);
    }
}
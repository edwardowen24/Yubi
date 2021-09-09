package com.example.yubi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    boolean p1f1 = false, p1f2 = false, p1f3 = false, p2f1 = false, p2f2 = false, p2f3 = false;
    ArrayList<Integer> p1ArrayList = new ArrayList<>();
    ArrayList<Integer> p2ArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
        Button cbtn = findViewById(R.id.checkButton);
        Button rbtn = findViewById(R.id.reset);


//        p1click();

            p1finger1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    p1f1 = true;
                    p1ArrayList.add(1);
                }
            });
            p1finger2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    p1f2 = true;
                    p1ArrayList.add(2);
                }
            });
            p1finger3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    p1f3 = true;
                    p1ArrayList.add(3);
                }
            });
//        p2click();
            p2finger1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    p2f1 = true;
                    p2ArrayList.add(1);
                }
            });
            p2finger2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    p2f2 = true;
                    p2ArrayList.add(2);
                }
            });
            p2finger3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    p2f3 = true;
                    p2ArrayList.add(3);
                }
            });


            cbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    boolean arrayListCheck = p1ArrayList.equals(p2ArrayList);
                    int roundInt;
                    if (p1ArrayList.equals(p2ArrayList)) {
                        roundInt = 1;
                    } else {
                        roundInt = -1;
                    }
                    round.setText(String.valueOf(roundInt));
                }
            });
            rbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameInit(round);
                }
            });


    }

    public void gameInit(TextView round){
        p1f1 = false; p1f2 = false; p1f3 = false; p2f1 = false; p2f2 = false; p2f3 = false;
        p1ArrayList.clear();
        p2ArrayList.clear();
        round.setText(String.valueOf(0));
    }
//    public void p1click(){
//        p1finger1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p1f1 = true;
//            }
//        });
//        p1finger2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p1f2 = true;
//            }
//        });
//        p1finger3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p1f3 = true;
//            }
//        });
//    }
//
//    public void p2click(){
//        p2finger1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p2f1 = true;
//            }
//        });
//        p2finger2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p2f2 = true;
//            }
//        });
//        p2finger3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p2f3 = true;
//            }
//        });
//    }
}
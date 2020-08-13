package com.example.ai8puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startgame(View view) {

        Intent intent=new Intent(this,Chooes.class);
        startActivity(intent);
    }


    public void existGame(View view) {
        finish();
        System.exit(0);
    }
}


package com.example.ai8puzzle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Chooes extends AppCompatActivity
{

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooes);
        mContext=this;
        ImageView ai = (ImageView) findViewById(R.id.AI_IMAGE);
        ImageView human = (ImageView)findViewById(R.id.Human_Image);
        ImageView back= (ImageView)findViewById(R.id.imageView2);

        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,PuzzleGame.class);
                intent.putExtra(PuzzleGame.PLAYING_MODE, "ai");
                startActivity(intent);

            }
        });
        human.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,PuzzleGame.class);
                intent.putExtra(PuzzleGame.PLAYING_MODE, "human");
                startActivity(intent);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}

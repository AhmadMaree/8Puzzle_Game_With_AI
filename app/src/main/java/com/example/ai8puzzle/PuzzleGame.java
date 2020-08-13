package com.example.ai8puzzle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleGame extends AppCompatActivity implements MyAdapter.onTileClickedListner {
    public static final String PLAYING_MODE = "PLAYING_MODE";
    private static final String REC_VIEW_LAYOUT="REC";


    RecyclerView recyclerView;
    MyAdapter adapter;
    GridLayoutManager gridLayoutManager;
    private int moves = 0;
    int numofcoulm;
    ArrayList<Tiels> mTiels;
    MyAdapter.onTileClickedListner listner;
    public  int curpos=9 ;
    public int curpos1=9;
    boolean ishuman;
    static ArrayList<String> ai_moves = new ArrayList<>();
    public int i = 0;
    public int ai_moves_size;
    private boolean changed =false;
    PopupWindow changeSortPopUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_puzzle);
        numofcoulm=calculateNoOfColumns(getApplicationContext()); // for calcute number of coulm  but i will not use it.
        recyclerView = findViewById(R.id.rec_view);
        mTiels=new ArrayList<>();
        listner = this;
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter(this,mTiels,listner);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);



        //get Extra data from previous class
        Intent intent=getIntent();
        if(intent.hasExtra(PLAYING_MODE)){
            if(intent.getStringExtra(PLAYING_MODE).equals("ai")){
                ishuman=false;
                ImageView btn_forward_hint = findViewById(R.id.btn_hint);
                btn_forward_hint.setBackground(getDrawable(R.drawable.ai));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn_forward_hint.setBackground(getDrawable(R.drawable.ai));
                }
            }else{
                ishuman = true;
                ImageView btn_forward_hint = findViewById(R.id.btn_hint);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btn_forward_hint.setBackground(getDrawable(R.drawable.noun_hint_3194438));
                }
            }
        }
        Log.d("ahmad", "initlizationNumbers:ahmad33"+ishuman);
        initlizationNumbers();
            
    }

    private void initlizationNumbers() {
        for (int i = 1; i < curpos1+1; i++) {
            Tiels tile = new Tiels(i);
            tile.setNum(i);
            tile.setPos(i);
            tile.setFlagnumber(curpos1);
            mTiels.add(tile);
            Log.d("ffff", "hint:"+i);
        }
        Log.d("ffff", "hint: dsadasdasda15"+curpos1);

        if(curpos1==9) {
            shuffleTiles();
            while (!isPuzzleSolvable() && !isWin()) {
                shuffleTiles();

            }
        }else if (curpos1 == 16){
            shuffleTiles1();
            while (!isPuzzleSolvable1() && !isWin()) {
                shuffleTiles1();

            }
        }else if(curpos1 ==25){
            shuffleTiles2();
            while (!isPuzzleSolvable() && !isWin()) {
                shuffleTiles2();

            }
        }

        curpos = getCurrentPosition();
        setRecylceViewAdapter();
        Log.d("ahmad", "initlizationNumbers:ahmad1");
        if (!ishuman) {
            if (curpos1 == 9) {
                String a[][] = new String[3][3];
                int o = 0;
                for (int i = 0; i < 3; i++) {
                    if (mTiels.get(o).getNum() == 9)
                        a[0][i] = "";
                    else
                        a[0][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 3; i++) {
                    if (mTiels.get(o).getNum() == 9)
                        a[1][i] = "";
                    else
                        a[1][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 3; i++) {
                    if (mTiels.get(o).getNum() == 9)
                        a[2][i] = "";
                    else
                        a[2][i] = mTiels.get(o).getNum() + "";
                    o++;
                }
                Log.d("ffff", "hint:"+a.length);
                SloutionPuzzle solution = new SloutionPuzzle();
                Log.d("ahmad", "initlizationNumbers:ahmad10");
                solution.set_a(a);
                Log.d("ahmad", "initlizationNumbers:ahmad11");
                solution.test();
                Log.d("ahmad", "initlizationNumbers:ahmad12");
                solution.releaseResources();
                Log.d("ahmad", "initlizationNumbers:ahmad13");
                solution = null;
                ai_moves_size = ai_moves.size();
                Log.d("ahmad", "initlizationNumbers:ahmad14");
                TextView tv = findViewById(R.id.best_move);
                tv.setText("Best Moves :" + ai_moves_size);

            } else if (curpos1 == 16) {
                Log.d("ffff", "hint: dsadasdasd2");
                String a[][] = new String[4][4];
                int o = 0;
                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[0][i] = "";
                    else
                        a[0][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[1][i] = "";
                    else
                        a[1][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[2][i] = "";
                    else
                        a[2][i] = mTiels.get(o).getNum() + "";
                    o++;
                }
                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[3][i] = "";
                    else
                        a[3][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                Log.d("ffff", "hint:"+a.length);
                Solution16Puzzle solution = new Solution16Puzzle();
                Log.d("ffff", "hint: firsthint1");
                solution.set_a(a);
                Log.d("ffff", "hint: firsthint2");
                solution.test();
                Log.d("ffff", "hint: firsthint3");
                solution.releaseResources();
                solution = null;
                ai_moves_size = ai_moves.size();
                Log.d("ffff", "hint: firsthint4");
                TextView tv = findViewById(R.id.best_move);
                tv.setText("Best Moves :" + ai_moves_size);

            } else if (curpos1 == 25) {
                String c[][] = new String[5][5];
                int o = 0;
                for (int i = 0; i < 5; i++) {
                    if (mTiels.get(o).getNum() == 25)
                        c[0][i] = "";
                    else
                        c[0][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 5; i++) {
                    if (mTiels.get(o).getNum() == 25)
                        c[1][i] = "";
                    else
                        c[1][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 5; i++) {
                    if (mTiels.get(o).getNum() == 25)
                        c[2][i] = "";
                    else
                        c[2][i] = mTiels.get(o).getNum() + "";
                    o++;
                }
                for (int i = 0; i < 5; i++) {
                    if (mTiels.get(o).getNum() == 25)
                        c[3][i] = "";
                    else
                        c[3][i] = mTiels.get(o).getNum() + "";
                    o++;
                }
                for (int i = 0; i < 5; i++) {
                    if (mTiels.get(o).getNum() == 25)
                        c[4][i] = "";
                    else
                        c[4][i] = mTiels.get(o).getNum() + "";
                    o++;
                }
                SloutionPuzzle solution = new SloutionPuzzle();
                solution.set_a(c);
                solution.test();
                solution.releaseResources();
                solution = null;
                ai_moves_size = ai_moves.size();

                TextView tv = findViewById(R.id.best_move);
                tv.setText("Best Moves :" + ai_moves_size);
            }
        }

    }
    public boolean isWin() {
        Log.d("adsada", "isWin: "+mTiels.size());
        for (int i =1 ; i < mTiels.size()-1; i++) {
            if (mTiels.get(i).getNum() > mTiels.get(i + 1).getNum()) {
                Log.d("adsada1", "isWin1: " + mTiels.get(i).getNum());
                return false;
            }
        }
        return true;
    }
    private boolean isPuzzleSolvable() {

        int inv_count = 0;
        for (int num = 0; num < mTiels.size()-1; num++) {
            if (mTiels.get(num).getNum() == 9  || mTiels.get(num).getNum() == 25)
                continue;
            for (int num2 = num + 1; num2 < mTiels.size(); num2++) {
                if (mTiels.get(num).getNum() > mTiels.get(num2).getNum())
                    inv_count++;
            }

        }
        Log.e("inv", inv_count + "");
        return inv_count % 2 == 0;
    }
    private boolean isPuzzleSolvable1() {

        int inv_count = 0;
        for (int num = 0; num < mTiels.size()-1; num++) {
            if (mTiels.get(num).getNum() == 16)
                continue;
            for (int num2 = num + 1; num2 < mTiels.size(); num2++) {
                if (mTiels.get(num).getNum() > mTiels.get(num2).getNum())
                    inv_count++;
            }

        }
        Log.e("inv1", inv_count + "");
        return inv_count % 2 == 0;
    }
    private void setRecylceViewAdapter() {

        MyAdapter adapter = new MyAdapter(this, mTiels, listner);
        adapter.setmTiels(mTiels);
        recyclerView.setAdapter(adapter);

    }
    private void setRecylceViewAdapter1() {
        recyclerView = findViewById(R.id.rec_view);
        mTiels=new ArrayList<>();
        listner=this;
        if(curpos == 16){

            RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,4);
            recyclerView.setLayoutManager(layoutManager);
            Log.d("ffff", "hint: dsadasdasda16");
        }else if (curpos == 25){
            RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,5);
            recyclerView.setLayoutManager(layoutManager);
        }
        else if(curpos == 9 ){
            RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,3);
            recyclerView.setLayoutManager(layoutManager);
        }
        MyAdapter adapter = new MyAdapter(this, mTiels, listner);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    private int getCurrentPosition() {
        for (int i = 0; i < mTiels.size(); i++) {
            if (curpos1 == 9) {
                if (mTiels.get(i).getNum() == 9) {
                    return i + 1;
                }
            } else if (curpos1 == 16){

                if (mTiels.get(i).getNum() == 16) {
                    return i + 1;
                }
           }else if (curpos1==25){
                if(mTiels.get(i).getNum() == 25)
                return i + 1;
            }

        }
        return -1;
    }
    // shuffle the Number (خؤبشهم)
    private void shuffleTiles() {

        int shuffleNumbers = (int) (Math.random() * 3);
        for (int i = 0; i < shuffleNumbers; i++) {
            int pos1 = (int) (Math.random() * 8) + 1;
            int pos2 = (int) (Math.random() * 8) + 1;
            swapTiles(pos1, pos2);
        }
        swapTiles(9, (int) (Math.random() * 8) + 1);

    }
    private void shuffleTiles1() {

        int shuffleNumbers = (int) (Math.random() * 3);
        for (int i = 0; i < shuffleNumbers; i++) {
            int pos1 = (int) (Math.random() * 8) + 1;
            int pos2 = (int) (Math.random() * 8) + 1;
            swapTiles(pos1, pos2);
        }
       // swapTiles(16, 1);
        swapTiles(16, 15);
        swapTiles(15, 14);
        swapTiles(14, 13);
        swapTiles(13, 12);
        swapTiles(12, 11);
        swapTiles(11, 10);
        swapTiles(10,1);

    }
    private void shuffleTiles2() {

        int shuffleNumbers = (int) (Math.random() * 5);
        for (int i = 0; i < shuffleNumbers; i++) {
            int pos1 = (int) (Math.random() * 24) + 1;
            int pos2 = (int) (Math.random() * 24) + 1;
            swapTiles(pos1, pos2);
        }
        swapTiles(25, (int) (Math.random() * 24) + 1);

    }
    private void swapTiles(int position, int currentPos) {

        Collections.swap(mTiels, position - 1, currentPos - 1);
    }
    ///////////////////////////////////////
    @Override
    public void onTileClicked(int position) {
        if(curpos1 == 9) {
            if (isValidPosition(position)) {
                changed = true;
                swapTiles(position, curpos);
                curpos = position;
                setRecylceViewAdapter();
                moves++;
                updateMoves_tv();
                if (isWin()) {
                    Toast.makeText(this, "You Win", Toast.LENGTH_SHORT).show();
                    Log.d("ggg", "isWin: " + mTiels.size());
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int height = displayMetrics.heightPixels;
                    int width = displayMetrics.widthPixels;
                    showSortPopup(this, new Point(height / 2, width / 2));

                }
            }
        }else if(curpos1 == 16){
            if (isValidPosition1(position)) {
                changed = true;
                swapTiles(position, curpos);
                curpos = position;
                setRecylceViewAdapter();
                moves++;
                updateMoves_tv();
                if (isWin()) {
                    Toast.makeText(this, "You Win", Toast.LENGTH_SHORT).show();
                    Log.d("ggg", "isWin: " + mTiels.size());
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int height = displayMetrics.heightPixels;
                    int width = displayMetrics.widthPixels;
                    showSortPopup(this, new Point(height / 2, width / 2));

                }
            }
        }
    }
    private boolean isValidPosition(int position) {
        switch (curpos) {
            case 1:
                if (position == 2 || position == 4)
                    return true;
                break;
            case 2:
                if (position == 1 || position == 3 || position == 5)
                    return true;
                break;
            case 3:
                if (position == 2 || position == 6)
                    return true;
                break;
            case 4:
                if (position == 1 || position == 5 || position == 7)
                    return true;
                break;
            case 5:
                if (position == 2 || position == 4 || position == 6 || position == 8)
                    return true;
                break;
            case 6:
                if (position == 3 || position == 5 || position == 9)
                    return true;
                break;
            case 7:
                if (position == 4 || position == 8)
                    return true;
                break;
            case 8:
                if (position == 5 || position == 7 || position == 9)
                    return true;
                break;
            case 9:
                if (position == 6 || position == 8)
                    return true;

                break;
        }
        return false;
    }
    private boolean isValidPosition1(int position) {
        switch (curpos) {
            case 1:
                if (position == 2 || position == 5)
                    return true;
                break;
            case 2:
                if (position == 1 || position == 3 || position == 6)
                    return true;
                break;
            case 3:
                if (position == 2 || position == 4 || position == 7)
                    return true;
                break;
            case 4:
                if (position == 3 || position == 8)
                    return true;
                break;
            case 5:
                if (position == 1 || position == 6 || position == 9)
                    return true;
                break;
            case 6:
                if (position == 2 || position == 5 || position == 7||position==10)
                    return true;
                break;
            case 7:
                if (position == 3 || position == 6|| position == 8 || position == 11)
                    return true;
                break;
            case 8:
                if (position == 4 || position == 7 || position == 12)
                    return true;
                break;
            case 9:
                if (position == 5 || position == 10 || position==13)
                    return true;
            case 10:
                if (position == 6 || position == 9|| position == 11 || position == 14)
                    return true;
            case 11:
                if (position == 7 || position == 10|| position == 12 || position == 15)
                    return true;

            case 12:
                if (position == 8 || position == 11|| position == 16)
                    return true;
            case 13:
                if (position == 9 ||  position == 14)
                    return true;

            case 14:
                if (position == 10 || position == 13|| position == 15)
                    return true;
            case 15:
                if (position == 11|| position == 14|| position == 16)
                    return true;
            case 16:
                if (position == 12 || position == 15)
                    return true;
                break;
        }
        return false;
    }
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int nUMOfColumns = (int) (dpWidth / 180);
        return nUMOfColumns;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menugrid, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.grid3) {
            curpos = 9;
            curpos1=9;
            setRecylceViewAdapter1();
            initlizationNumbers();

        }  if (id == R.id.grid4) {
            curpos = 16;
            curpos1=16;
            Log.d("curpos", "onOptionsItemSelected: "+curpos1);
            setRecylceViewAdapter1();
            initlizationNumbers();

        }else if (id == R.id.grid5){
            curpos=25;
            curpos1=25;
            setRecylceViewAdapter1();
            initlizationNumbers();
        }
        return super.onOptionsItemSelected(item);
    }

    public void restartGame(View view) {
        mTiels.clear();
        moves = 0;
        i = 0;
        ai_moves.clear();
        ai_moves_size = 0;
        initlizationNumbers();
        if (ishuman)
            updateMoves_tv();
        setRecylceViewAdapter();
        TextView tv = findViewById(R.id.best_move);
        tv.setText("Best Moves :" + ai_moves_size);
    }

    private void updateMoves_tv() {
        TextView tv_moves = findViewById(R.id.move);
        tv_moves.setText("Moves : " + moves);

    }

    public void hint(View view) {
        if (curpos1 == 9) {
        if (ishuman) {

                String a[][] = new String[3][3];
                int o = 0;
                for (int i = 0; i < 3; i++) {
                    if (mTiels.get(o).getNum() == 9)
                        a[0][i] = "";
                    else
                        a[0][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 3; i++) {
                    if (mTiels.get(o).getNum() == 9)
                        a[1][i] = "";
                    else
                        a[1][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 3; i++) {
                    if (mTiels.get(o).getNum() == 9)
                        a[2][i] = "";
                    else
                        a[2][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                SloutionPuzzle solution = new SloutionPuzzle();
                solution.set_a(a);
                solution.test();
                solution.releaseResources();
                solution = null;
                ai_moves_size = ai_moves.size();
                TextView tv = findViewById(R.id.best_move);
                tv.setText("Best Moves :" + ai_moves_size);
                if (!isWin())
                    doMove(ai_moves.get(0));
//
            } else {
                Log.d("ffff", "hint: dsadasdasda");
                CountDownTimer countDownTimer = new CountDownTimer(1000 * ai_moves_size, 500) {
                    @Override
                    public void onTick(long l) {
                        if (i < ai_moves_size) {
                            doMove(ai_moves.get(i));
                            i++;
                        }
                    }

                    @Override
                    public void onFinish() {
                    }
                }.start();
            }
        }// for hint solve by ai
        else if(curpos1 == 16){
            if(ishuman){
                String a[][] = new String[4][4];
                int o = 0;
                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[0][i] = "";
                    else
                        a[0][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[1][i] = "";
                    else
                        a[1][i] = mTiels.get(o).getNum() + "";
                    o++;
                }

                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[2][i] = "";
                    else
                        a[2][i] = mTiels.get(o).getNum() + "";
                    o++;
                }
                for (int i = 0; i < 4; i++) {
                    if (mTiels.get(o).getNum() == 16)
                        a[3][i] = "";
                    else
                        a[3][i] = mTiels.get(o).getNum() + "";
                    o++;
                }
                Log.d("ffff", "hint: firsthint");
                Solution16Puzzle solution = new Solution16Puzzle();
                Log.d("ffff", "hint: firsthint1");
                solution.set_a(a);
                Log.d("ffff", "hint: firsthint2");
                solution.test();
                Log.d("ffff", "hint: firsthint3");
                solution.releaseResources();
                solution = null;
                ai_moves_size = ai_moves.size();
                Log.d("ffff", "hint: firsthint4");
                TextView tv = findViewById(R.id.best_move);
                tv.setText("Best Moves :" + ai_moves_size);
                if (!isWin())
                    doMove(ai_moves.get(0));

            }else {
                CountDownTimer countDownTimer = new CountDownTimer(1000 * ai_moves_size, 500) {
                    @Override
                    public void onTick(long l) {
                        if (i < ai_moves_size) {
                            doMove(ai_moves.get(i));
                            i++;
                        }
                    }

                    @Override
                    public void onFinish() {
                    }
                }.start();
            }
        }
    }
    private void doMove(String ai_move) {
        if(curpos1 == 9) {
            switch (ai_move) {
                case "UP":
                    int pos = curpos - 3;
                    ai_doMove(pos);
                    break;

                case "DOWN":
                    pos = curpos + 3;
                    ai_doMove(pos);
                    break;

                case "LEFT":
                    pos = curpos - 1;
                    ai_doMove(pos);
                    break;

                case "RIGHT":
                    pos = curpos + 1;
                    ai_doMove(pos);
                    break;
            }
        }else if(curpos1 == 16){
            switch (ai_move) {
                case "UP":
                    int pos = curpos - 4;
                    ai_doMove(pos);
                    break;

                case "DOWN":
                    pos = curpos + 4;
                    ai_doMove(pos);
                    break;

                case "LEFT":
                    pos = curpos - 1;
                    ai_doMove(pos);
                    break;

                case "RIGHT":
                    pos = curpos + 1;
                    ai_doMove(pos);
                    break;
            }
        }
    }
    public void ai_doMove(int position) {
        swapTiles(position, curpos);
        curpos = position;
        setRecylceViewAdapter();
        moves++;
        if (isWin()) {
            Toast.makeText(this, "You Win", Toast.LENGTH_SHORT).show();
            Log.d("win", "ai_doMove: dasdasdasd");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            showSortPopup(this, new Point(height / 2, width / 2));

        }
    }
    private void showSortPopup(final Activity context, Point p) {
        // Inflate the popup_layout.xml
        ConstraintLayout viewGroup = (ConstraintLayout) context.findViewById(R.id.game_layout);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.winner_page, viewGroup, false);

        // Creating the PopupWindow
        changeSortPopUp = new PopupWindow(context);
        changeSortPopUp.setContentView(layout);
//        changeSortPopUp.setWidth(200);
        changeSortPopUp.setHeight(750);
        changeSortPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        // Clear the default translucent background
        changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());


        // Displaying the popup at the specified location, + offsets.
        changeSortPopUp.showAtLocation(layout, Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0 + OFFSET_X, 0 + OFFSET_Y);


        // Getting a reference to Close button, and close the popup when clicked.
        ImageView close = (ImageView) layout.findViewById(R.id.restartbtn);
        TextView textView=(TextView)findViewById(R.id.move) ;
        textView.setText("Moves : " + moves
        );
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
                restartGame(null);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Runtime.getRuntime().gc();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

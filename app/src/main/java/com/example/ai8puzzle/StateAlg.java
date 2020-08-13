package com.example.ai8puzzle;

import android.util.Log;

import java.util.ArrayList;

public class StateAlg  implements Comparable<StateAlg> {

    public int f;
    public int level;
    public  String [][] Tiles;
    String move ="";
    ArrayList<String> pathOfParent = new ArrayList<>();

    public StateAlg  (String [][]j , int level){
        int a= j.length;
        this.Tiles = new String[a][a];
        for(int i =0 ; i< a;i++){
            for (int y =0 ; y<a ; y++){
                this.Tiles [i][y]=j[i][y];
            }
        }
        this.level=level;
        this.f=manhattanDistance()+level;
    }


    private  int manhattanDistance(){
        int sum=0;
        int [] index = new int [2];
        int a= SloutionPuzzle.goal.length;
        for (int i =0;i<a;i++){
            for(int j=0; j<a;j++){
                if(this.Tiles[i][j].trim().isEmpty()){
                    continue;
                }
                index = findIndex(Integer.parseInt((this.Tiles[i][j])));
                sum = sum + (Math.abs(i - index[0]) + Math.abs(j - index[1]));
            }
        }


        return sum;
    }

    private int [] findIndex(int element){
        int [] index = new int[2];
        int a= SloutionPuzzle.goal.length;
        for (int i =0 ; i<a;i++){
            for(int j=0;j<a;j++){
                if (SloutionPuzzle.goal[i][j].trim().isEmpty()) {
                    continue;
                }
                if (SloutionPuzzle.goal[i][j].trim().equals(String.valueOf(element))) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }

        return index;
    }


    public  ArrayList<StateAlg> computeSuccessor (StateAlg main){
        ArrayList<StateAlg>successor = new ArrayList<StateAlg>();
        Log.d("ahmad", "initlizationNumbers:ahmads5");
        int a= this.Tiles.length;
        for(int i=0;i<a;i++){
            for(int j =0;j<a ; j++){
                if(main.Tiles[i][j].trim().isEmpty()){
                    //////////////for up //////////////////////
                    if(i-1>=0){
                         String [] [] arr = new  String[a][a];
                        for(int x=0;x<a;x++){
                            for(int y=0;y<a;y++){

                                arr[x][y]=main.Tiles[x][y];
                            }
                        }
                        arr = swapping(arr,i,j,i-1,j);
                        StateAlg next = new StateAlg( arr, main.level+1);
                        next.move="UP";
                        for(int n=0; n<this.pathOfParent.size(); n++){
                            next.pathOfParent.add(this.pathOfParent.get(n));
                        }
                        next.pathOfParent.add(next.move);
                        successor.add(next);
                    }
                    ///////////////////for down //////////////////
                    if(i+1 < a){
                        String [] [] arr = new  String[a][a];
                        for(int x=0;x<a;x++){
                            for(int y=0;y<a;y++){

                                arr[x][y]=main.Tiles[x][y];
                            }
                        }
                        arr = swapping(arr,i,j,i+1,j);
                        StateAlg next = new StateAlg( arr, main.level+1);
                        next.move="DOWN";
                        for(int n=0; n<this.pathOfParent.size(); n++){
                            next.pathOfParent.add(this.pathOfParent.get(n));
                        }
                        next.pathOfParent.add(next.move);
                        successor.add(next);
                    }


                    //////////////////for left ////////////////

                    if(j-1>=0){
                        String [] [] arr = new  String[a][a];
                        for(int x=0;x<a;x++){
                            for(int y=0;y<a;y++){

                                arr[x][y]=main.Tiles[x][y];
                            }
                        }
                        arr = swapping(arr,i,j,i,j-1);
                        StateAlg next = new StateAlg( arr, main.level+1);
                        next.move="LEFT";
                        for(int n=0; n<this.pathOfParent.size(); n++){
                            next.pathOfParent.add(this.pathOfParent.get(n));
                        }
                        next.pathOfParent.add(next.move);
                        successor.add(next);
                    }

                    ///////////////////for right//////////////////
                    if(j+1 < a){
                        String [] [] arr = new  String[a][a];
                        for(int x=0;x<a;x++){
                            for(int y=0;y<a;y++){

                                arr[x][y]=main.Tiles[x][y];
                            }
                        }
                        arr = swapping(arr,i,j,i,j+1);
                        StateAlg next = new StateAlg( arr, main.level+1);
                        next.move="RIGHT";
                        for(int n=0; n<this.pathOfParent.size(); n++){
                            next.pathOfParent.add(this.pathOfParent.get(n));
                        }
                        next.pathOfParent.add(next.move);
                        successor.add(next);
                    }

                }
            }
        }

        return successor;
    }


    private String [][] swapping(String [][]array , int current_row,int current_column,int next_row , int next_column){
        String [][] memory = array;
        String temp = memory [current_row][current_column];
        memory [current_row][current_column]=memory[next_row][next_column];
        memory [next_row][next_column]=temp;
        return  memory;
    }

    @Override
    public int compareTo(StateAlg stateAlg) {
        if (this.f == stateAlg.f) {
            return ((this.manhattanDistance() - stateAlg.manhattanDistance()));
        }
        return this.f - stateAlg.f;
    }
}


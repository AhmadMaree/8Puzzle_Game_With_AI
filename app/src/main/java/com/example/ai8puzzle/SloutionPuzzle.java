package com.example.ai8puzzle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import static com.example.ai8puzzle.PuzzleGame.ai_moves;
public class SloutionPuzzle {


    public static String[][] goal = {{"", "1", "2"}, {"3", "4", "5"}, {"6", "7", "8"}};
    //for goal 2 will solve later
    public static String[][] goal1 = {{"1", "2", "3"}, {"4", "", "5"}, {"6", "7", "8"}};
    public static PriorityQueue<StateAlg> pq;
    public static ArrayList<StateAlg> expanded;
    String a[][];//initial state
    ///construcor
    public SloutionPuzzle() {
    }
    public SloutionPuzzle(StateAlg first) {
        if (first == null) {
        }
        pq = new PriorityQueue<>();
        expanded = new ArrayList<>();
        pq.add(first);
        ArrayList<StateAlg> list = new ArrayList<StateAlg>();
        while (!pq.isEmpty()) {
            StateAlg current = pq.poll();    //returns and deletes the first node of the priority queue and store it in 'current' variable.
            expanded.add(current);            //Adds current object to the 'end' list<State> which holds all the expanded nodes
            if (Arrays.deepEquals(current.Tiles, goal)) {
                break;
            }
            list = current.computeSuccessor (current);
            for (int i = 0; i < list.size(); i++) {
                if (checkIfExpandedStatesAreNotInExpanded(list.get(i)) && checkIfExpandedStatesAreNotInQueue(list.get(i))) {
                    pq.add(list.get(i));
                }
            }
            Log.d("ahmad", "initlizationNumbers:"+pq.size());

        }
    }
    private boolean checkIfExpandedStatesAreNotInExpanded(StateAlg s) {
        for (StateAlg e : pq) {
            if (s.Tiles.equals(e))
                return false;
            if (Arrays.deepEquals(s.Tiles, e.Tiles)) {
                return false;
            }

        }
        return true;
    }

    private boolean checkIfExpandedStatesAreNotInQueue(StateAlg s) {
        for (StateAlg e : expanded) {
            if (s.Tiles.equals(e))
                return false;
            if (Arrays.deepEquals(s.Tiles, e.Tiles)) {
                if (s.f < e.f) {
                    e.f = s.f;
                    e.move = s.move;
                    e.level = s.level;
                    e.pathOfParent = s.pathOfParent;
                }
                return false;
            }
        }
        return true;
    }


    public void set_a(String a[][]) {
        this.a = a;
    }
    public void releaseResources() {
        pq.clear();
        pq = null;
        expanded.clear();
        expanded = null;
    }

    public void test() {
        long startTime = System.currentTimeMillis();
        Log.d("ahmad", "initlizationNumbers:ahmads1");
        StateAlg state = new StateAlg(a, 0);
        Log.d("ahmad", "initlizationNumbers:ahmads2");
        new SloutionPuzzle(state);

        StringBuilder stringBuilder = new StringBuilder();
        StateAlg lastOne = null;

        for ( StateAlg states : expanded) {
            for (int l = 0; l < 3; l++) {
                for (int m = 0; m < 3; m++) {
                    stringBuilder.append(states.Tiles[l][m] + ",");
                }

            }

            stringBuilder.setLength(0);
            lastOne = states;
        }
        ai_moves = lastOne.pathOfParent;
    }
}

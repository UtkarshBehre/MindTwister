package com.mindtwister.mindtwister.memorymatrix.utility;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Utkarsh on 21-07-2016.
 */
public class UtilityMethodsForMemoryMatrix {

    //call this method from game activity to flash the required number of tiles for the time given
    public static void flashRandomTiles(int totalNumberOfTiles, int noOfTilesToFlash, ArrayList<Button> buttonsList, long TilesflashingTime) {
        HashMap<Integer, Boolean> gridSet = RandomTilesSelector(totalNumberOfTiles, noOfTilesToFlash);
        flashTheTiles(gridSet, buttonsList, TilesflashingTime);
    }

    //this method gives a gridset with all integer numbers as key and value boolean initiation with false
    public static HashMap<Integer, Boolean> getGridSet(int numberOfTiles) {
        HashMap<Integer, Boolean> gridSet = new HashMap<Integer, Boolean>();
        for (int i = 1; i <= numberOfTiles; i++) {
            gridSet.put(i, false);
        }
        return gridSet;
    }

    // this will return gridset with random values in key,value pairs of gridset as true
    public static HashMap<Integer, Boolean> RandomTilesSelector(int totalNumberOfTiles, int noOfTilesToFlash) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= totalNumberOfTiles; i++) {
            list.add(i);
        }

        //shuffles the elements of the list so we can extract first noOfTilesToFlash as list
        Collections.shuffle(list);
        List<Integer> randomNumbersList = new ArrayList<>();
        for (int i = 0; i < noOfTilesToFlash; i++) {
            randomNumbersList.add(list.get(i));
        }

        //using getGridSet method to get gridset(HashMap) with all values of key,value pairs as false
        HashMap<Integer, Boolean> gridSet = getGridSet(totalNumberOfTiles);

        //sets values of the randomed tiles to be flashed in key,value pair of gridset to be true
        for (Integer i : randomNumbersList) {
            gridSet.put(i, true);
        }
        return gridSet;
    }

    public static void flashTheTiles(HashMap<Integer, Boolean> gridSet, ArrayList<Button> buttonsList, long TilesFlashingTime) {

    }

}

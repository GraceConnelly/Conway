package com.theironyard.charlotte;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {


    private static String[][] getFileContents(String fileName) {
        File f = new File(fileName);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[][] conway = new String[100][100];

        for (int i = 0;i < 100;i++) {
            String thisLine = fileScanner.nextLine();
            for (int j = 0;j < 100;j++) {
                conway[i][j] = Character.toString(thisLine.charAt(j));
            }
        }

        return conway;
    }

    private static String[][] deepArrayCopy(String[][] input) {
        String[][] copy = new String[input.length][input.length];

        for (int i = 0;i < input.length;i++) {
            copy[i] = Arrays.copyOf(input[i], input.length);
        }

        return copy;
    }


    public static int getAliveNeighbors(String[][] array, int i, int j) {
        // method determines Moore's neighborhood of alive neighbors
        int aliveCnt = 0;

            for (int row = i-1; row <= i+1 ; row++) {
                for (int col = j-1; col <= j+1; col++) {
                    if(row >= 0 && row < array.length && col >= 0 && col < array[row].length) {  //takes care of array boundaries
                        if (array[row][col].equals("1")) {
                            aliveCnt++;
                        }
                    }
                }
            }
            if(array[i][j].equals("1")){
                aliveCnt--;
            }
        return aliveCnt;
    }
        public static int bensGetAliveNeighbors(String[][] array, int i, int j) {
            // method determines Moore's neighborhood of alive neighbors

        int count = 0;
        if (i > 0 && j > 0 && array[i - 1][j - 1].equals("1")) {
            count++;        }
        if (i > 0 && array[i - 1][j].equals("1")) {
            count++;        }
        if (i > 0 && j < array.length - 1 && array[i - 1][j + 1].equals("1")) {
            count++;        }
        if (j > 0 && array[i][j - 1].equals("1")) {
            count++;        }
        if (j < array.length - 1 && array[i][j + 1].equals("1")) {
            count++;        }
        if (i < array.length - 1 && j > 0 && array[i + 1][j - 1].equals("1")) {
            count++;        }
        if (i < array.length - 1 && array[i + 1][j].equals("1")) {
            count++;        }
        if (i < array.length - 1 && j < array.length - 1 && array[i + 1][j + 1].equals('1')) {
            count++;        }
        return count;
    }

    private static boolean livesOrDies(int aliveCnt, boolean isAlive){
//        Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
//        Any live cell with two or three live neighbours lives on to the next generation.
//        Any live cell with more than three live neighbours dies, as if by overpopulation.
//        Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        switch (aliveCnt){
            case 0:
            case 1:
                if(isAlive == true) {
                    isAlive = false;
                }
                break;
            case 2:
            case 3:
                if(isAlive == false && aliveCnt == 3){
                    isAlive = true;
                }
                break;
            default:
                isAlive = false;
                break;
        }
        return isAlive;
    }
    public static void main(String[] args) throws IOException {
        // create a currentTick of the input.txt array to work with
        String[][] currentTick = getFileContents("input.txt");
        //while loop to go through specified number of ticks
        int ticks = 0;
        while (ticks < 1000) {
            String[][] workingCopy = deepArrayCopy(currentTick);
            //Iterate through the entire currentTick array. Determine if the cell lives or dies. Store the status in the workingCopy array
            for (int i = 0; i < currentTick.length; i++) {
                for (int j = 0; j < currentTick[i].length; j++) {
                    if (livesOrDies(getAliveNeighbors(currentTick, i, j), (currentTick[i][j].equals("1")))) {
                        workingCopy[i][j] = "1";
                    } else{
                        workingCopy[i][j] = "0";
                    }
                }
            }
            currentTick = deepArrayCopy(workingCopy);
            //System.out.println(Arrays.deepToString(currentTick[0]));
            ticks++;
        }
        // compare currentTick to output using Arrays.deepEquals to
        String[][] givenOut = getFileContents("output.txt");
        if(Arrays.deepEquals(currentTick,givenOut)) {
            System.out.println("Victory!!");

            System.out.println(Arrays.deepToString(givenOut[0]));
            System.out.println(Arrays.deepToString(currentTick[0]));
        }
        else System.out.println("Try again sucker!");
//        System.out.println(Arrays.deepToString(givenOut[0]));
//        System.out.println(Arrays.deepToString(currentTick[0]));
    }
}

package com.company;

import java.util.*;

public class Main {

    private static int[] negRowNumbers;
    private static int[] posRowNumbers;
    private static int[] posColNumbers;
    private static int[] negColNumbers;

    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        int rowNumber = scanner.nextInt();
        int columnNumber = scanner.nextInt();
        posRowNumbers = new int[rowNumber];
        negRowNumbers = new int[rowNumber];
        posColNumbers = new int[columnNumber];
        negColNumbers = new int[columnNumber];
        int[][] intTable = new int[rowNumber][columnNumber];

        for(int j = 0; j < rowNumber; j++){
            posRowNumbers[j] = scanner.nextInt();
        }

        for(int j = 0; j < rowNumber; j++){
            negRowNumbers[j] = scanner.nextInt();
        }

        for(int j = 0; j < columnNumber; j++){
            posColNumbers[j] = scanner.nextInt();
        }

        for(int j = 0; j < columnNumber; j++){
            negColNumbers[j] = scanner.nextInt();
        }

        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < columnNumber; j++){
                intTable[i][j] = scanner.nextInt();
            }
        }

        Hashtable<String, Variable> variables = new Hashtable<>();
        String key;

        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < columnNumber; j++){
                if(i < rowNumber - 1 && intTable[i + 1][j] == intTable[i][j]){
                    Variable var =  new Variable(i + 1, j, i, j);
                    key = i + " " + j;
                    variables.put(key, var);
                    key = (i+1) + " " + j;
                    variables.put(key, var);
                }
                if(j < columnNumber - 1 && intTable[i][j + 1] == intTable[i][j]){
                    Variable var =  new Variable(i, j+1, i, j);
                    key = i + " " + j;
                    variables.put(key, var);
                    key = i + " " + (j+1);
                    variables.put(key, var);
                }
            }
        }

    }

    /*
    public int[][] CSP_BackTracking(int[][] table){

    }
    public boolean isComplete(int[][] table){

    }
    */

}
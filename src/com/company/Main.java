package com.company;

import java.util.*;

public class Main {


    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        int rowNumber = scanner.nextInt();
        int columnNumber = scanner.nextInt();
        int[] posRowNumbers = new int[rowNumber];
        int[] negRowNumbers = new int[rowNumber];
        int[] posColNumbers = new int[columnNumber];
        int[] negColNumbers = new int[columnNumber];
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

        ArrayList<Integer> position = new ArrayList<>();
        Hashtable<ArrayList<Integer>, Variable> variables = new Hashtable<>();

        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < columnNumber; j++){
                if(i < rowNumber - 1 && intTable[i + 1][j] == intTable[i][j]){
                    position.add(i);
                    position.add(j);
                    variables.put(position, new Variable(i + 1, j, i, j));
                    position = new ArrayList<>();
                    position.add(i + 1);
                    position.add(j);
                    variables.put(position, new Variable(i + 1, j, i, j));
                    position = new ArrayList<>();
                }
                if(j < columnNumber - 1 && intTable[i][j + 1] == intTable[i][j]){
                    position.add(i);
                    position.add(j);
                    variables.put(position, new Variable(i, j + 1, i, j));
                    position = new ArrayList<>();
                    position.add(i);
                    position.add(j + 1);
                    variables.put(position, new Variable(i, j + 1, i, j));
                    position = new ArrayList<>();
                }
            }
        }
    }
}
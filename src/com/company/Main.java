package com.company;

import java.util.Scanner;

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
        Variable[] variables = new Variable[(rowNumber * columnNumber) / 2];
        for(int i = 0, varIndex = 0; i < rowNumber; i++){
            for(int j = 0; j < columnNumber; j++){
                if(i > 0 && intTable[i - 1][j] == intTable[i][j]){
                    variables[varIndex] = new Variable(i - 1, j, i, j);
                    varIndex++;
                }
                if(i < rowNumber - 1 && intTable[i + 1][j] == intTable[i][j]){
                    variables[varIndex] = new Variable(i + 1, j, i, j);
                    varIndex++;
                }
                if(j > 0 && intTable[i][j - 1] == intTable[i][j]){
                    variables[varIndex] = new Variable(i, j - 1, i, j);
                    varIndex++;
                }
                if(j < columnNumber - 1 && intTable[i][j + 1] == intTable[i][j]){
                    variables[varIndex] = new Variable(i, j + 1, i, j);
                    varIndex++;
                }
            }
        }

    }
}

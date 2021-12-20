package com.company;

import java.util.Scanner;

public class Main {

//main
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

        Table table = new Table(rowNumber,columnNumber,intTable);
    }
}

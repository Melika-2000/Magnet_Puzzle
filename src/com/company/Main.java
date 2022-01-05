package com.company;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        long startTime = System.currentTimeMillis();
        String key;
        ArrayList<Variable> variables = new ArrayList<>();

        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < columnNumber; j++){
                if(i < rowNumber - 1 && intTable[i + 1][j] == intTable[i][j]){
                    Variable var =  new Variable(i + 1, j, i, j);
                    variables.add(var);
                }
                if(j < columnNumber - 1 && intTable[i][j + 1] == intTable[i][j]){
                    Variable var =  new Variable(i, j+1, i, j);
                    variables.add(var);
                }
            }
        }

        ArrayList<Variable> vList = new ArrayList<>();
        Utility function = new Utility(rowNumber, columnNumber, negRowNumbers,
                posRowNumbers, negColNumbers, posColNumbers);


        ArrayList<Variable> result;
        result = function.CSP_BackTracking(vList, variables);
        if(result == null){
            System.out.println("no answers found");
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            System.out.print("Execution time is " + timeElapsed + " miliseconds");
            System.exit(2);
        }

        //chap nahayii
        Hashtable<String, Variable> v = function.listToHash(variables);
        Variable var;
        String value;
        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < columnNumber; j++) {
                key = i + " " + j;
                var = v.get(key);
                value = var.selectedValue(i,j);
                System.out.print(value + " ");
            }
            System.out.println();
        }
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.print("Execution time is " + timeElapsed + " miliseconds");
    }
}
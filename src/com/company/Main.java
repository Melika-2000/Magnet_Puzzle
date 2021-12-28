package com.company;

import java.util.*;

public class Main {

    private static int[] negRowNumbers;
    private static int[] posRowNumbers;
    private static int[] posColNumbers;
    private static int[] negColNumbers;
    private static int rowNumber;
    private static int columnNumber;
    private static Hashtable<String, Variable> variables;

    public void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        rowNumber = scanner.nextInt();
        columnNumber = scanner.nextInt();
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

        variables = new Hashtable<>();
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

        ArrayList<Variable> vList = new ArrayList<>();
        vList = this.CSP_BackTracking(vList);

        Variable var;
        String value;
        for(int i = 0; i < rowNumber; i++){
            for(int j = 0; j < columnNumber; j++) {
                key = i + " " + j;
                var = variables.get(key);
                value = var.selectedValue(i,j);
                System.out.print(value + " ");
            }
            System.out.println();
        }

    }


    public ArrayList<Variable> CSP_BackTracking(ArrayList<Variable> vList){
        Utility function = new Utility(rowNumber,columnNumber);

        if(isComplete(vList))
            return vList;

        vList = function.AC3(vList);
        for(int i =0; i<vList.size(); i++){
            if(vList.get(i).getDomainSize() == 0)
                return null;
        }
        ArrayList<Variable> vPrimList = notInA(vList);
        Variable var = function.MRV(vPrimList);
        ArrayList<Integer> ordering = new ArrayList<>();
        ordering = function.LCV(vList,var);
        for(int v : ordering){
            var.selectValue(v);
            vList.add(var);
            vList = function.forwardChecking(vList,var,v);
            for(int i =0; i<vList.size(); i++){ //ye funcion jadid?
                if(vList.get(i).getDomainSize() == 0)
                    return null;
            }
            ArrayList<Variable> result = CSP_BackTracking(vList);
            if(result != null)
                return result;
        }
        return null;

    }

    public ArrayList<Variable> notInA (ArrayList<Variable> variableArrayList){
        for(int i = 0; i<variables.size(); i++){

            //peida kardan value haii ke dar A nistan (meghdar dehi nashodan)

        }
    }

    public boolean isComplete(ArrayList<Variable> vList) {
        Variable var;
        int count=0;
        int sum = 0;

        if(vList.size() == 0)
            return false;
        for(int i=0; i<vList.size(); i++){
            var = vList.get(i);
            if (var.getIsMagnet())
                count++;
        }
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                sum += negColNumbers[j];
                sum += posColNumbers[j];
            }
            sum += negRowNumbers[i];
            sum += posRowNumbers[i];
        }

        if(sum == count)
            return true;
        return false;
    }

}
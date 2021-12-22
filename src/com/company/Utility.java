package com.company;

import java.util.ArrayList;
import java.util.Hashtable;

public class Utility {
    int rowNumber;
    int colomnNumber;

    public Utility(int rowNumber, int colomnNumber) {
        this.rowNumber = rowNumber;
        this.colomnNumber = colomnNumber;
    }

    public void AC3(ArrayList<Variable> variables){

    }

    public void forwardChecking(int rowNumber, int columnNumber, Hashtable<ArrayList<Integer>,Variable> variables, Variable selectedVar, int selectedDomain){
        int[][] varPosition = selectedVar.getPositions();
        ArrayList<Integer> position = new ArrayList<>();
    }
}

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

    public void AC3(ArrayList<Variable> variables) {

    }

    public void forwardChecking(Hashtable<String, Variable> variables, Variable selectedVar, int selectedDomain) {
        int[][] varPosition = selectedVar.getPositions();
        String neighStr;
        if (varPosition[1][0] >= 1) {
            neighStr = "";
            neighStr += (varPosition[1][0] - 1) + " " + varPosition[1][1];
            Variable neighVar = variables.get(neighStr);
            int[] neighDom = neighVar.getDomain();
            if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
            neighVar.setDomain(neighDom);
            variables.put(neighStr, neighVar);
            if(varPosition[1][0] == varPosition[0][0]){
                neighStr = "";
                neighStr += (varPosition[0][0] - 1) + " " + varPosition[0][1];
                neighVar = variables.get(neighStr);
                neighDom = neighVar.getDomain();
                if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
                neighVar.setDomain(neighDom);
                variables.put(neighStr, neighVar);
            }
        }
        if (varPosition[1][1] >= 1) {
            neighStr = "";
            neighStr += varPosition[1][0] + " " + (varPosition[1][1] - 1);
            Variable neighVar = variables.get(neighStr);
            int[] neighDom = neighVar.getDomain();
            if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
            neighVar.setDomain(neighDom);
            variables.put(neighStr, neighVar);
            if(varPosition[1][1] == varPosition[0][1]){
                neighStr = "";
                neighStr += varPosition[0][0] + " " + (varPosition[0][1] - 1);
                neighVar = variables.get(neighStr);
                neighDom = neighVar.getDomain();
                if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
                neighVar.setDomain(neighDom);
                variables.put(neighStr, neighVar);
            }
        }
        if (varPosition[0][0] < rowNumber - 1) {
            neighStr = "";
            neighStr += (varPosition[0][0] + 1) + " " + varPosition[1][1];
            Variable neighVar = variables.get(neighStr);
            int[] neighDom = neighVar.getDomain();
            if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
            neighVar.setDomain(neighDom);
            variables.put(neighStr, neighVar);
            if (varPosition[0][1] == varPosition[1][1]) {
                neighStr = "";
                neighStr += varPosition[1][0] + " " + (varPosition[1][1] + 1);
                neighVar = variables.get(neighStr);
                neighDom = neighVar.getDomain();
                if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
                neighVar.setDomain(neighDom);
                variables.put(neighStr, neighVar);
            }
        }
        if (varPosition[0][1] < colomnNumber - 1) {
            neighStr = "";
            neighStr += varPosition[1][0] + " " + (varPosition[1][1] + 1);
            Variable neighVar = variables.get(neighStr);
            int[] neighDom = neighVar.getDomain();
            if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
            neighVar.setDomain(neighDom);
            variables.put(neighStr, neighVar);
            if (varPosition[0][0] == varPosition[1][0]) {
                neighStr = "";
                neighStr += (varPosition[1][0] + 1) + " " + varPosition[1][1];
                neighVar = variables.get(neighStr);
                neighDom = neighVar.getDomain();
                if (neighDom[selectedDomain] == 1) neighDom[selectedDomain] = 0;
                neighVar.setDomain(neighDom);
                variables.put(neighStr, neighVar);
            }
        }
    }

    public void MRV(ArrayList<Variable> variables) {

    }

    public void LCV(ArrayList<Variable> variables) {

    }
}

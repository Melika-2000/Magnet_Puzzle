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

    public Variable MRV(ArrayList<Variable> variables) {
        int min = 3;
        int index=0;
        for (int i=0; i< variables.size(); i++){
            if(variables.get(i).getDomainSize() < min) {
                index = i;
                min = variables.get(i).getDomainSize();
            }
        }
        return variables.get(index);
    }

    public int LCV(Hashtable<String, Variable> variables, Variable variable){ //not complete

        if (variable.getDomain()[0] == 1)
            return 0;

        int[][] varPos = variable.getPositions();
        int y1 = varPos[1][0]; //i
        int y2 = varPos[0][0]; //i+1 || i
        int x1 = varPos[1][1]; //j
        int x2 = varPos[0][1]; //j+1 || j
        String key = "";

        for(int i=1; i<3; i++){
            if(variable.getDomain()[i] == 0)
                continue;
            if(variable.isHorizontal()){ // y1 == y2
                if(y1 > 0){ //mitune hamsaye balaii dashte bashe
                    key = y1-1 + " " + x1;
                    Variable top_left_n = variables.get(key);
                    key = y1-1 + " " + x2;
                    Variable top_right_n = variables.get(key);
                }
                if(x1 > 0){ //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1-1);
                    Variable left_n = variables.get(key);
                }
                if(y2 < rowNumber-1){ //mitune hamsaye paiini dashte bashe
                    key = y2+1 + " " + x1;
                    Variable bottom_left_n = variables.get(key);
                    key = y2+1 + " " + x2;
                    Variable bottom_right_n = variables.get(key);
                }
                if(x2 < colomnNumber-1){ //mitune hamsaye rast dashte bashe
                    key = y1 + " " + x2+1;
                    Variable right_n = variables.get(key);
                }

            }
            else { // variable is vertical. x1 == x2

                if(y1 > 0){ //mitune hamsaye balaii dashte bashe
                    key = y1-1 + " " + x1;
                    Variable top_n = variables.get(key);
                }
                if(x1 > 0){ //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1-1);
                    Variable left_top_n = variables.get(key);
                    key = y2 + " " + (x1-1);
                    Variable left_bottom_n = variables.get(key);
                }
                if(y2 < rowNumber-1){ //mitune hamsaye paiini dashte bashe
                    key = y2+1 + " " + x1;
                    Variable bottom_n = variables.get(key);
                }
                if(x2 < colomnNumber-1){ //mitune hamsaye rast dashte bashe
                    key = y1 + " " + (x2+1);
                    Variable right_top_n = variables.get(key);
                    key = y2 + " " + (x2+1);
                    Variable right_bottom_n = variables.get(key);
                }

            }

        }
        return 0;
    }


}

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

    public int LCV(Hashtable<String, Variable> variables, Variable variable){

        if (variable.getDomain()[0] == 1)
            return 0;

        int count = 0;
        int min = 1000;
        int minValue = 0;
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

                    //ye hamseye balayii ba 2 khune mojaver
                    if(top_left_n.equals(top_right_n)){
                        if(variable.getDomain()[i] == top_left_n.getDomain()[i])
                            count++;
                    }
                    else{ //2 hamsaye ba yekhune mojaver
                        //top_lef_n halat amudi va ofoghish farghi nemikone
                        if(i == 1 && top_left_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && top_left_n.getDomain()[1]==1)
                            count++;
                        if(top_right_n.isHorizontal()) {
                            if (i == 1 && top_right_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && top_right_n.getDomain()[1] == 1)
                                count++;
                        }
                        else if(top_right_n.getDomain()[i] == 1)
                                count++;
                    }
                }

                if(x1 > 0){ //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1-1);
                    Variable left_n = variables.get(key);

                    if(left_n.getOtherPositionY(key) > y1) {
                        if (left_n.getDomain()[i] == 1)
                            count++;
                    }
                    else{
                        if(i == 1 && left_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && left_n.getDomain()[1]==1)
                            count++;
                    }
                }

                if(y2 < rowNumber-1){ //mitune hamsaye paiini dashte bashe
                    key = y2+1 + " " + x1;
                    Variable bottom_left_n = variables.get(key);
                    key = y2+1 + " " + x2;
                    Variable bottom_right_n = variables.get(key);

                    //ye hamseye balayii ba 2 khune mojaver
                    if(bottom_left_n.equals(bottom_right_n)){
                        if(variable.getDomain()[i] == bottom_left_n.getDomain()[i])
                            count++;
                    }
                    else{ //2 hamsaye ba yekhune mojaver
                        //bottom_right_n halat amudi va ofoghish farghi nemikone
                        if(i == 1 && bottom_right_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && bottom_right_n.getDomain()[1]==1)
                            count++;
                        if(bottom_left_n.isHorizontal()) {
                            if (i == 1 && bottom_left_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && bottom_left_n.getDomain()[1] == 1)
                                count++;
                        }
                        else if(bottom_left_n.getDomain()[i] == 1)
                            count++;
                    }
                }

                if(x2 < colomnNumber-1){ //mitune hamsaye rast dashte bashe
                    key = y1 + " " + x2+1;
                    Variable right_n = variables.get(key);

                    if(right_n.getOtherPositionY(key) < y1) {
                        if (right_n.getDomain()[i] == 1)
                            count++;
                    }
                    else{
                        if(i == 1 && right_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && right_n.getDomain()[1]==1)
                            count++;
                    }
                }
            }
            else { // variable is vertical. x1 == x2

                if(y1 > 0){ //mitune hamsaye balaii dashte bashe
                    key = y1-1 + " " + x1;
                    Variable top_n = variables.get(key);

                    if(top_n.getOtherPositionX(key) > x1){
                        if (top_n.getDomain()[i] == 1)
                            count++;
                    }
                    else{
                        if(i == 1 && top_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && top_n.getDomain()[1]==1)
                            count++;
                    }
                }

                if(x1 > 0){ //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1-1);
                    Variable left_top_n = variables.get(key);
                    key = y2 + " " + (x1-1);
                    Variable left_bottom_n = variables.get(key);

                    //ye hamseye samte chap ba 2 khune mojaver
                    if(left_top_n.equals(left_bottom_n)){
                        if(variable.getDomain()[i] == left_top_n.getDomain()[i])
                            count++;
                    }
                    else{ //2 hamsaye ba yekhune mojaver
                        //lef_top_n halat amudi va ofoghish farghi nemikone
                        if(i == 1 && left_top_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && left_top_n.getDomain()[1]==1)
                            count++;
                        if(left_bottom_n.isHorizontal()){
                            if(left_bottom_n.getDomain()[i] == 1)
                                count++;
                        }
                        else {
                            if (i == 1 && left_bottom_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && left_bottom_n.getDomain()[1] == 1)
                                count++;
                        }
                    }
                }

                if(y2 < rowNumber-1){ //mitune hamsaye paiini dashte bashe
                    key = y2+1 + " " + x1;
                    Variable bottom_n = variables.get(key);

                    if(bottom_n.getOtherPositionX(key) < x1){
                        if (bottom_n.getDomain()[i] == 1)
                            count++;
                    }
                    else{
                        if(i == 1 && bottom_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && bottom_n.getDomain()[1]==1)
                            count++;
                    }
                }

                if(x2 < colomnNumber-1){ //mitune hamsaye rast dashte bashe
                    key = y1 + " " + (x2+1);
                    Variable right_top_n = variables.get(key);
                    key = y2 + " " + (x2+1);
                    Variable right_bottom_n = variables.get(key);

                    //ye hamseye samte chap ba 2 khune mojaver
                    if(right_top_n.equals(right_bottom_n)){
                        if(variable.getDomain()[i] == right_bottom_n.getDomain()[i])
                            count++;
                    }
                    else{ //2 hamsaye ba yekhune mojaver
                        //right_bottom_n halat amudi va ofoghish farghi nemikone
                        if(i == 1 && right_bottom_n.getDomain()[2]==1)
                            count++;
                        if(i == 2 && right_bottom_n.getDomain()[1]==1)
                            count++;
                        if(right_top_n.isHorizontal()){
                            if(right_top_n.getDomain()[i] == 1)
                                count++;
                        }
                        else {
                            if (i == 1 && right_top_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && right_top_n.getDomain()[1] == 1)
                                count++;
                        }
                    }
                }

            }

            if(count < min){
                min = count;
                minValue = i;
            }
            count = 0;
        }

        //az bein domain[1] va domain[2] uni ke mahdudiat kamtari
        //ijad mikone ro barmigardune
        return minValue;
    }

}

package com.company;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Utility {
    int rowNumber;
    int columnNumber;

    public Utility(int rowNumber, int colomnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public void AC3(Hashtable<String, Variable> variables) {
        Hashtable<String, Variable> copyOfVariables = new Hashtable<>();
        Enumeration<String> ee = variables.keys();

        while(ee.hasMoreElements()){
            String key = ee.nextElement();
            if(variables.get(key).getDomain()[0] == 1 && (variables.get(key).getDomain()[1] == 1 || variables.get(key).getDomain()[2] == 1)){
                copyOfVariables.put(key,variables.get(key));
            }
        }
        boolean contradiction = false;
        ee = copyOfVariables.keys();

        while (copyOfVariables.size() != 0 && !contradiction) {
            String key = ee.nextElement();
            Variable selectedVar = copyOfVariables.get(key);
            copyOfVariables.remove(key,selectedVar);
            copyOfVariables.remove(selectedVar.getOtherPosition(key), selectedVar);
            ArrayList<Variable> neighVars = new ArrayList<>();
            int[][] XAndYPos = new int[8][2];
            int count = 0;
            int[][] temp;

            if (selectedVar.getPositions()[1][0] >= 1) {
                if(copyOfVariables.containsKey((selectedVar.getPositions()[1][0] - 1) + " " + selectedVar.getPositions()[1][1])) {
                    neighVars.add(copyOfVariables.get((selectedVar.getPositions()[1][0] - 1) + " " + selectedVar.getPositions()[1][1]));
                    temp = neighVars.get(0).getPositions();
                    XAndYPos[count][0] = 1;
                    if (temp[0][1] == selectedVar.getPositions()[1][0] - 1 && temp[0][1] == selectedVar.getPositions()[1][1])
                        XAndYPos[count][1] = 0;
                    else XAndYPos[count][1] = 1;
                    count++;
                }
                if (selectedVar.isHorizontal()) {
                    if(copyOfVariables.containsKey((selectedVar.getPositions()[0][0] - 1) + " " + selectedVar.getPositions()[0][1])) {
                        neighVars.add(copyOfVariables.get((selectedVar.getPositions()[0][0] - 1) + " " + selectedVar.getPositions()[0][1]));
                        temp = neighVars.get(neighVars.size() - 1).getPositions();
                        XAndYPos[count][0] = 0;
                        if (temp[0][1] == selectedVar.getPositions()[0][0] - 1 && temp[0][1] == selectedVar.getPositions()[0][1])
                            XAndYPos[count][1] = 0;
                        else XAndYPos[count][1] = 1;
                        count++;
                    }
                }
            }

            if (selectedVar.getPositions()[1][1] >= 1) {
                if(copyOfVariables.containsKey(selectedVar.getPositions()[1][0] + " " + (selectedVar.getPositions()[1][1] - 1))) {
                    neighVars.add(copyOfVariables.get(selectedVar.getPositions()[1][0] + " " + (selectedVar.getPositions()[1][1] - 1)));
                    temp = neighVars.get(neighVars.size() - 1).getPositions();
                    XAndYPos[count][0] = 1;
                    if (temp[0][1] == selectedVar.getPositions()[1][0] && temp[0][1] == selectedVar.getPositions()[1][1] - 1)
                        XAndYPos[count][1] = 0;
                    else XAndYPos[count][1] = 1;
                    count++;
                }
                if (!selectedVar.isHorizontal()) {
                    if(copyOfVariables.containsKey(selectedVar.getPositions()[0][0] + " " + (selectedVar.getPositions()[0][1] - 1))) {
                        neighVars.add(copyOfVariables.get(selectedVar.getPositions()[0][0] + " " + (selectedVar.getPositions()[0][1] - 1)));
                        temp = neighVars.get(neighVars.size() - 1).getPositions();
                        XAndYPos[count][0] = 0;
                        if (temp[0][1] == selectedVar.getPositions()[0][0] && temp[0][1] == selectedVar.getPositions()[0][1] - 1)
                            XAndYPos[count][1] = 0;
                        else XAndYPos[count][1] = 1;
                        count++;
                    }
                }
            }
            if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
                if(copyOfVariables.containsKey((selectedVar.getPositions()[0][0] + 1) + " " + selectedVar.getPositions()[0][1])) {
                    neighVars.add(copyOfVariables.get((selectedVar.getPositions()[0][0] + 1) + " " + selectedVar.getPositions()[0][1]));
                    temp = neighVars.get(neighVars.size() - 1).getPositions();
                    XAndYPos[count][0] = 0;
                    if (temp[0][1] == selectedVar.getPositions()[0][0] && temp[0][1] == selectedVar.getPositions()[0][1] - 1)
                        XAndYPos[count][1] = 0;
                    else XAndYPos[count][1] = 1;
                    count++;
                }
                if (selectedVar.getPositions()[0][0] == selectedVar.getPositions()[1][0]) {
                    if(copyOfVariables.containsKey((selectedVar.getPositions()[1][0] + 1) + " " + selectedVar.getPositions()[1][1])) {
                        neighVars.add(copyOfVariables.get((selectedVar.getPositions()[1][0] + 1) + " " + selectedVar.getPositions()[1][1]));
                        temp = neighVars.get(neighVars.size() - 1).getPositions();
                        XAndYPos[count][0] = 1;
                        if (temp[0][1] == selectedVar.getPositions()[0][0] && temp[0][1] == selectedVar.getPositions()[0][1] - 1)
                            XAndYPos[count][1] = 0;
                        else XAndYPos[count][1] = 1;
                        count++;
                    }
                }
            }
            if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
                if(copyOfVariables.containsKey(selectedVar.getPositions()[0][0] + " " + (selectedVar.getPositions()[0][1] + 1))) {
                    neighVars.add(copyOfVariables.get(selectedVar.getPositions()[0][0] + " " + (selectedVar.getPositions()[0][1] + 1)));
                    temp = neighVars.get(neighVars.size() - 1).getPositions();
                    XAndYPos[count][0] = 0;
                    if (temp[0][1] == selectedVar.getPositions()[0][0] && temp[0][1] == selectedVar.getPositions()[0][1] - 1)
                        XAndYPos[count][1] = 0;
                    else XAndYPos[count][1] = 1;
                    count++;
                }
                if (selectedVar.getPositions()[0][1] == selectedVar.getPositions()[1][1]) {
                    if(copyOfVariables.containsKey(selectedVar.getPositions()[1][0] + " " + (selectedVar.getPositions()[1][1] + 1))) {
                        neighVars.add(copyOfVariables.get(selectedVar.getPositions()[1][0] + " " + (selectedVar.getPositions()[1][1] + 1)));
                        temp = neighVars.get(neighVars.size() - 1).getPositions();
                        XAndYPos[count][0] = 0;
                        if (temp[0][1] == selectedVar.getPositions()[0][0] && temp[0][1] == selectedVar.getPositions()[0][1] - 1)
                            XAndYPos[count][1] = 0;
                        else XAndYPos[count][1] = 1;
                    }
                }
            }

            for (int i = 0; i < neighVars.size(); i++) {
                if (removeValues(selectedVar, neighVars.get(i), XAndYPos[i][0], XAndYPos[i][1])) {
                    if(neighVars.get(i).getDomain()[1] == 0 && neighVars.get(i).getDomain()[2] == 0){
                        contradiction = true;
                        copyOfVariables.put(neighVars.get(i).getPositions()[0][0] + " " + neighVars.get(i).getPositions()[0][1], neighVars.get(i));
                        copyOfVariables.put(neighVars.get(i).getPositions()[1][0] + " " + neighVars.get(i).getPositions()[1][1], neighVars.get(i));
                        ee = copyOfVariables.keys();
                    }
                }
            }
        }
    }

    public boolean removeValues(Variable X, Variable Y, int x_position, int y_position) {
        boolean[][] remove = {{true, true}, {true, true}};
        if (Y.getDomain()[2] == 1) {
            if (X.getDomain()[1] == 1) {
                if (y_position == 1 && x_position == 1) {
                    remove[0][0] = false;
                } else if (y_position == 0 && x_position == 0) {
                    remove[0][0] = false;
                }
            }
            if (X.getDomain()[2] == 1) {
                if (y_position == 1 && x_position == 0) {
                    remove[0][1] = false;
                } else if (y_position == 0 && x_position == 1) {
                    remove[0][1] = false;
                }
            }
        }
        if (Y.getDomain()[1] == 1) {
            if (X.getDomain()[1] == 1) {
                if (y_position == 1 && x_position == 0) {
                    remove[1][1] = false;
                } else if (y_position == 0 && x_position == 1) {
                    remove[1][1] = false;
                }
            }
            if (X.getDomain()[2] == 1) {
                if (y_position == 1 && x_position == 1) {
                    remove[1][0] = false;
                } else if (y_position == 0 && x_position == 0) {
                    remove[1][0] = false;
                }
            }
        }
        int[] newDomain = {1, remove[1][0] && remove[1][1] ? 0 : 1, remove[0][0] && remove[0][1] ? 0 : 1};
        Y.setDomain(newDomain);
        return ((remove[1][0] && remove[1][1]) || (remove[0][0] && remove[0][1]));
    }

    public void forwardChecking(Hashtable<String, Variable> variables, Variable selectedVar, int selectedDomain) {
        String neighStr;
        if (selectedVar.getPositions()[1][0] >= 1) {
            neighStr = (selectedVar.getPositions()[1][0] - 1) + " " + selectedVar.getPositions()[1][1];
            if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                variables.get(neighStr).getDomain()[selectedDomain] = 0;
            if (selectedVar.getPositions()[1][0] == selectedVar.getPositions()[0][0]) {
                neighStr = (selectedVar.getPositions()[0][0] - 1) + " " + selectedVar.getPositions()[0][1];
                if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                    variables.get(neighStr).getDomain()[selectedDomain] = 0;
            }
        }
        if (selectedVar.getPositions()[1][1] >= 1) {
            neighStr = selectedVar.getPositions()[1][0] + " " + (selectedVar.getPositions()[1][1] - 1);
            if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                variables.get(neighStr).getDomain()[selectedDomain] = 0;
            if (selectedVar.getPositions()[1][1] == selectedVar.getPositions()[0][1]) {
                neighStr = selectedVar.getPositions()[0][0] + " " + (selectedVar.getPositions()[0][1] - 1);
                if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                    variables.get(neighStr).getDomain()[selectedDomain] = 0;
            }
        }
        if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
            neighStr = (selectedVar.getPositions()[0][0] + 1) + " " + selectedVar.getPositions()[0][1];
            if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                variables.get(neighStr).getDomain()[selectedDomain] = 0;
            if (selectedVar.getPositions()[0][0] == selectedVar.getPositions()[1][0]) {
                neighStr = (selectedVar.getPositions()[1][0] + 1) + " " + selectedVar.getPositions()[1][1];
                if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                    variables.get(neighStr).getDomain()[selectedDomain] = 0;
            }
        }
        if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
            neighStr = selectedVar.getPositions()[1][0] + " " + (selectedVar.getPositions()[1][1] + 1);
            if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                variables.get(neighStr).getDomain()[selectedDomain] = 0;
            if (selectedVar.getPositions()[0][1] == selectedVar.getPositions()[1][1]) {
                neighStr = selectedVar.getPositions()[1][0] + " " + (selectedVar.getPositions()[1][1] + 1);
                if (variables.get(neighStr).getDomain()[selectedDomain] == 1)
                    variables.get(neighStr).getDomain()[selectedDomain] = 0;
            }
        }
    }

    public Variable MRV(ArrayList<Variable> variables) {
        int min = 3;
        int index = 0;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getDomainSize() < min) {
                index = i;
                min = variables.get(i).getDomainSize();
            }
        }
        return variables.get(index);
    }

    public ArrayList<Integer> LCV(Hashtable<String, Variable> variables, Variable variable) {
        ArrayList<Integer> ordering = new ArrayList<>();
        if (variable.getDomain()[0] == 1)
            ordering.add(0);

        int count = 0;
        int min = 1000;
        int minValue = 0;
        int[][] varPos = variable.getPositions();
        int y1 = varPos[1][0]; //i
        int y2 = varPos[0][0]; //i+1 || i
        int x1 = varPos[1][1]; //j
        int x2 = varPos[0][1]; //j+1 || j
        String key = "";

        for (int i = 1; i < 3; i++) {

            if (variable.getDomain()[i] == 0)
                continue;

            if (variable.isHorizontal()) { // y1 == y2
                if (y1 > 0) { //mitune hamsaye balaii dashte bashe
                    key = y1 - 1 + " " + x1;
                    Variable top_left_n = variables.get(key);
                    key = y1 - 1 + " " + x2;
                    Variable top_right_n = variables.get(key);

                    //ye hamseye balayii ba 2 khune mojaver
                    if (top_left_n.equals(top_right_n)) {
                        if (variable.getDomain()[i] == top_left_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //top_lef_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && top_left_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && top_left_n.getDomain()[1] == 1)
                            count++;
                        if (top_right_n.isHorizontal()) {
                            if (i == 1 && top_right_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && top_right_n.getDomain()[1] == 1)
                                count++;
                        } else if (top_right_n.getDomain()[i] == 1)
                            count++;
                    }
                }

                if (x1 > 0) { //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1 - 1);
                    Variable left_n = variables.get(key);

                    if (left_n.getOtherPositionY(key) > y1) {
                        if (left_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && left_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && left_n.getDomain()[1] == 1)
                            count++;
                    }
                }

                if (y2 < rowNumber - 1) { //mitune hamsaye paiini dashte bashe
                    key = y2 + 1 + " " + x1;
                    Variable bottom_left_n = variables.get(key);
                    key = y2 + 1 + " " + x2;
                    Variable bottom_right_n = variables.get(key);

                    //ye hamseye balayii ba 2 khune mojaver
                    if (bottom_left_n.equals(bottom_right_n)) {
                        if (variable.getDomain()[i] == bottom_left_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //bottom_right_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && bottom_right_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && bottom_right_n.getDomain()[1] == 1)
                            count++;
                        if (bottom_left_n.isHorizontal()) {
                            if (i == 1 && bottom_left_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && bottom_left_n.getDomain()[1] == 1)
                                count++;
                        } else if (bottom_left_n.getDomain()[i] == 1)
                            count++;
                    }
                }

                if (x2 < columnNumber - 1) { //mitune hamsaye rast dashte bashe
                    key = y1 + " " + x2 + 1;
                    Variable right_n = variables.get(key);

                    if (right_n.getOtherPositionY(key) < y1) {
                        if (right_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && right_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && right_n.getDomain()[1] == 1)
                            count++;
                    }
                }
            } else { // variable is vertical. x1 == x2

                if (y1 > 0) { //mitune hamsaye balaii dashte bashe
                    key = y1 - 1 + " " + x1;
                    Variable top_n = variables.get(key);

                    if (top_n.getOtherPositionX(key) > x1) {
                        if (top_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && top_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && top_n.getDomain()[1] == 1)
                            count++;
                    }
                }

                if (x1 > 0) { //mitune hamsaye samte chap dashte bashe
                    key = y1 + " " + (x1 - 1);
                    Variable left_top_n = variables.get(key);
                    key = y2 + " " + (x1 - 1);
                    Variable left_bottom_n = variables.get(key);

                    //ye hamseye samte chap ba 2 khune mojaver
                    if (left_top_n.equals(left_bottom_n)) {
                        if (variable.getDomain()[i] == left_top_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //lef_top_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && left_top_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && left_top_n.getDomain()[1] == 1)
                            count++;
                        if (left_bottom_n.isHorizontal()) {
                            if (left_bottom_n.getDomain()[i] == 1)
                                count++;
                        } else {
                            if (i == 1 && left_bottom_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && left_bottom_n.getDomain()[1] == 1)
                                count++;
                        }
                    }
                }

                if (y2 < rowNumber - 1) { //mitune hamsaye paiini dashte bashe
                    key = y2 + 1 + " " + x1;
                    Variable bottom_n = variables.get(key);

                    if (bottom_n.getOtherPositionX(key) < x1) {
                        if (bottom_n.getDomain()[i] == 1)
                            count++;
                    } else {
                        if (i == 1 && bottom_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && bottom_n.getDomain()[1] == 1)
                            count++;
                    }
                }

                if (x2 < columnNumber - 1) { //mitune hamsaye rast dashte bashe
                    key = y1 + " " + (x2 + 1);
                    Variable right_top_n = variables.get(key);
                    key = y2 + " " + (x2 + 1);
                    Variable right_bottom_n = variables.get(key);

                    //ye hamseye samte chap ba 2 khune mojaver
                    if (right_top_n.equals(right_bottom_n)) {
                        if (variable.getDomain()[i] == right_bottom_n.getDomain()[i])
                            count++;
                    } else { //2 hamsaye ba yekhune mojaver
                        //right_bottom_n halat amudi va ofoghish farghi nemikone
                        if (i == 1 && right_bottom_n.getDomain()[2] == 1)
                            count++;
                        if (i == 2 && right_bottom_n.getDomain()[1] == 1)
                            count++;
                        if (right_top_n.isHorizontal()) {
                            if (right_top_n.getDomain()[i] == 1)
                                count++;
                        } else {
                            if (i == 1 && right_top_n.getDomain()[2] == 1)
                                count++;
                            if (i == 2 && right_top_n.getDomain()[1] == 1)
                                count++;
                        }
                    }
                }

            }

            if (count < min) { //ba farz inke variable ba domain tohi handle shode
                min = count;
                minValue = i;
            }
            count = 0;
        }
        ordering.add(minValue);
        if(minValue == 1 && variable.getDomain()[2] == 1)
            ordering.add(2);
        if(minValue == 2 && variable.getDomain()[1] == 1)
            ordering.add(1);

        return ordering;
    }

}

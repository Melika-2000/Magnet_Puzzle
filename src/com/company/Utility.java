package com.company;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class Utility {
    int rowNumber;
    int columnNumber;
    int[] posRowNumbers;
    int[] negRowNumbers;
    int[] posColNumbers;
    int[] negColNumbers;

    public Utility(int rowNumber, int columnNumber, int[] negRowNumbers,
                   int[] posRowNumbers, int[] negColNumbers, int[] posColNumbers) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.posRowNumbers = posRowNumbers;
        this.negRowNumbers = negRowNumbers;
        this.posColNumbers = posColNumbers;
        this.negColNumbers = negColNumbers;
    }

    public void AC3(ArrayList<Variable> originalVariables, ArrayList<Variable> vPrimeList) {
        boolean contradiction = false;
        ArrayList<Variable> variables = new ArrayList<>();
        for (Variable vprimelist : vPrimeList) {
            variables.add(vprimelist);
        }

        while (variables.size() != 0 && !contradiction) {
            Variable selectedVar = variables.get(0);
            variables.remove(0);
            ArrayList<Variable> neighVars = new ArrayList<>();
            int[][] XAndYPositions = new int[6][2];
            Variable tempVar;

            if (selectedVar.getPositions()[1][0] >= 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[1][0] - 1, selectedVar.getPositions()[1][1])) != null) {
                    XAndYPositions[neighVars.size()][0] = 1;
                    if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1])
                        XAndYPositions[neighVars.size()][1] = 0;
                    else {
                        XAndYPositions[neighVars.size()][1] = 1;
                        isACompleteNeighbor = true;
                    }
                    neighVars.add(tempVar);
                }
                if (selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[0][0] - 1, selectedVar.getPositions()[0][1])) != null) {
                        XAndYPositions[neighVars.size()][0] = 0;
                        if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1])
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            if (selectedVar.getPositions()[1][1] >= 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[1][0], (selectedVar.getPositions()[1][1] - 1))) != null) {
                    XAndYPositions[neighVars.size()][0] = 1;
                    if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] - 1)
                        XAndYPositions[neighVars.size()][1] = 0;
                    else {
                        XAndYPositions[neighVars.size()][1] = 1;
                        isACompleteNeighbor = true;
                    }
                    neighVars.add(tempVar);
                }
                if (!selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[0][0], (selectedVar.getPositions()[0][1] - 1))) != null) {
                        XAndYPositions[neighVars.size()][0] = 0;
                        if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] - 1)
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[0][0] + 1, selectedVar.getPositions()[0][1])) != null) {
                    XAndYPositions[neighVars.size()][0] = 0;
                    if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                        XAndYPositions[neighVars.size()][1] = 0;
                        isACompleteNeighbor = true;
                    } else XAndYPositions[neighVars.size()][1] = 1;
                    neighVars.add(tempVar);
                }
                if (selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[1][0] + 1, selectedVar.getPositions()[1][1])) != null) {
                        XAndYPositions[neighVars.size()][0] = 1;
                        if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1])
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
                boolean isACompleteNeighbor = false;
                if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] + 1)) != null) {
                    XAndYPositions[neighVars.size()][0] = 0;
                    if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] + 1) {
                        XAndYPositions[neighVars.size()][1] = 0;
                        isACompleteNeighbor = true;
                    } else XAndYPositions[neighVars.size()][1] = 1;
                    neighVars.add(tempVar);
                }
                if (!selectedVar.isHorizontal() && !isACompleteNeighbor) {
                    if ((tempVar = getVariable(vPrimeList, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] + 1)) != null) {
                        XAndYPositions[neighVars.size()][0] = 1;
                        if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] + 1)
                            XAndYPositions[neighVars.size()][1] = 0;
                        else XAndYPositions[neighVars.size()][1] = 1;
                        neighVars.add(tempVar);
                    }
                }
            }
            for (int i = 0; i < neighVars.size(); i++) {
                if (removeValues(originalVariables, selectedVar, neighVars.get(i), XAndYPositions[i][0], XAndYPositions[i][1])) {
                    if (neighVars.get(i).getDomainSize() == 0) {
                        contradiction = true;
                    }
/*
                    boolean find = false;
                    for (Variable variable : variables) {
                        if (variable == neighVars.get(i)) {
                            find = true;
                            break;
                        }
                    }
                    if(!find) variables.add(neighVars.get(i));
*/
                }
            }
        }
    }

    public Variable getVariable(ArrayList<Variable> variables, int y, int x) {
        for (Variable variable : variables) {
            if (variable.getPositions()[0][0] == y && variable.getPositions()[0][1] == x ||
                    variable.getPositions()[1][0] == y && variable.getPositions()[1][1] == x) {
                return variable;
            }
        }
        return null;
    }

    public boolean removeValues(ArrayList<Variable> variables, Variable X, Variable Y, int x_position, int y_position) {
        boolean[][] remove = {{false, false, false}, {false, false, false}, {false, false, false}};
        int[] tempPosRowNumbers = new int[rowNumber];
        int[] tempNegRowNumbers = new int[rowNumber];
        int[] tempPosColNumbers = new int[columnNumber];
        int[] tempNegColNumbers = new int[columnNumber];
        for (int i = 0; i < rowNumber; i++) {
            tempNegRowNumbers[i] = negRowNumbers[i];
            tempPosRowNumbers[i] = posRowNumbers[i];
        }
        for (int i = 0; i < columnNumber; i++) {
            tempNegColNumbers[i] = negColNumbers[i];
            tempPosColNumbers[i] = posColNumbers[i];
        }

        boolean removeDomain = false;
        int[] YDomain = new int[3];
        System.arraycopy(Y.domain, 0, YDomain, 0, 3);
        int[] XDomain = new int[3];
        System.arraycopy(X.domain, 0, XDomain, 0, 3);
        for (int y = 0; y < 3; y++) {
            if (YDomain[y] != 1) continue;
            for (int i = 0; i < 3; i++) {
                if (i != y) {
                    Y.domain[i] = 0;
                } else
                    Y.domain[i] = 1;
            }
            for (int x = 0; x < 3; x++) {
                if (XDomain[x] != 1) continue;
                for (int i = 0; i < 3; i++) {
                    if (i != x) {
                        X.domain[i] = 0;
                    } else
                        X.domain[i] = 1;
                }
                removeDomain = (y == 1 && x == 1 && y_position == x_position);
                if (removeDomain) {
                    remove[y][x] = removeDomain;
                    continue;
                }
                removeDomain = (y == 1 && x == 2 && y_position != x_position);
                if (removeDomain) {
                    remove[y][x] = removeDomain;
                    continue;
                }
                removeDomain = (y == 2 && x == 1 && y_position != x_position);
                if (removeDomain) {
                    remove[y][x] = removeDomain;
                    continue;
                }
                removeDomain = (y == 2 && x == 2 && y_position == x_position);
                if (removeDomain) {
                    remove[y][x] = removeDomain;
                    continue;
                }

                Set<Integer> rowNumbers = new HashSet<>();
                rowNumbers.add(X.getPositions()[0][0]);
                rowNumbers.add(X.getPositions()[1][0]);
                rowNumbers.add(Y.getPositions()[0][0]);
                rowNumbers.add(Y.getPositions()[1][0]);

                for (Integer rowNumber : rowNumbers) {
                    int emptyHomes = 0;
                    if (y == 1) {

                        tempPosRowNumbers[Y.getPositions()[0][0]] -= 1;
                        tempNegRowNumbers[Y.getPositions()[1][0]] -= 1;

                    } else if (y == 2) {

                        tempPosRowNumbers[Y.getPositions()[1][0]] -= 1;
                        tempNegRowNumbers[Y.getPositions()[0][0]] -= 1;
                    }
                    if (x == 1) {
                        tempPosRowNumbers[X.getPositions()[0][0]] -= 1;
                        tempNegRowNumbers[X.getPositions()[1][0]] -= 1;
                    } else if (x == 2) {

                        tempPosRowNumbers[X.getPositions()[1][0]] -= 1;
                        tempNegRowNumbers[X.getPositions()[0][0]] -= 1;
                    }

                    for (int i = 0; i < columnNumber; i++) {
                        Variable tempVar = getVariable(variables, rowNumber, i);
                        if (tempVar.getDomainSize() == 2 || tempVar.getDomainSize() == 3) {
                            if (tempVar.getDomain()[2] == 1 && tempVar.getDomain()[1] == 1){
                                emptyHomes++;
                            }
                            else if (tempVar.getPositions()[0][0] == rowNumber && tempVar.getPositions()[0][1] == i) {
                                if (tempVar.getDomain()[1] == 1) tempPosRowNumbers[rowNumber] -= 1;
                                else if (tempVar.getDomain()[2] == 1) tempNegRowNumbers[rowNumber] -= 1;
                            } else {
                                if (tempVar.getDomain()[2] == 1) tempPosRowNumbers[rowNumber] -= 1;
                                else if (tempVar.getDomain()[1] == 1) tempNegRowNumbers[rowNumber] -= 1;
                            }
                        } else if(tempVar.getDomainSize() == 1){
                            if (tempVar.getPositions()[0][0] == rowNumber && tempVar.getPositions()[0][1] == i) {
                                if (tempVar.domain[1] == 1) {
                                    tempPosRowNumbers[rowNumber] -= 1;
                                } else if (tempVar.domain[2] == 1) {
                                    tempNegRowNumbers[rowNumber] -= 1;
                                }
                            } else {
                                if (tempVar.domain[1] == 1) {
                                    tempNegRowNumbers[rowNumber] -= 1;
                                } else if (tempVar.domain[2] == 1) {
                                    tempPosRowNumbers[rowNumber] -= 1;
                                }
                            }
                        }
                    }
                    if (!(emptyHomes >= tempNegRowNumbers[rowNumber] + tempPosRowNumbers[rowNumber])) {
                        removeDomain = true;
                        for (int i = 0; i < this.rowNumber; i++) {
                            tempNegRowNumbers[i] = negRowNumbers[i];
                            tempPosRowNumbers[i] = posRowNumbers[i];
                        }
                        for (int i = 0; i < this.columnNumber; i++) {
                            tempNegColNumbers[i] = negColNumbers[i];
                            tempPosColNumbers[i] = posColNumbers[i];
                        }
                        break;
                    }
                    for (int i = 0; i < this.rowNumber; i++) {
                        tempNegRowNumbers[i] = negRowNumbers[i];
                        tempPosRowNumbers[i] = posRowNumbers[i];
                    }
                    for (int i = 0; i < this.columnNumber; i++) {
                        tempNegColNumbers[i] = negColNumbers[i];
                        tempPosColNumbers[i] = posColNumbers[i];
                    }
                }
                if (removeDomain) {
                    remove[y][x] = removeDomain;
                    continue;
                }
                Set<Integer> colNumbers = new HashSet<>();
                colNumbers.add(X.getPositions()[0][1]);
                colNumbers.add(X.getPositions()[1][1]);
                colNumbers.add(Y.getPositions()[0][1]);
                colNumbers.add(Y.getPositions()[1][1]);
                for (Integer columnNumber : colNumbers) {
                    int emptyHomes = 0;
                    if (Y.getDomain()[1] == 1) {

                        tempNegColNumbers[Y.getPositions()[1][1]] -= 1;
                        tempPosColNumbers[Y.getPositions()[0][1]] -= 1;

                    } else if (Y.getDomain()[2] == 1) {

                        tempNegColNumbers[Y.getPositions()[0][1]] -= 1;
                        tempPosColNumbers[Y.getPositions()[1][1]] -= 1;
                    }
                    if (X.getDomain()[1] == 1) {

                        tempNegColNumbers[X.getPositions()[1][1]] -= 1;
                        tempPosColNumbers[X.getPositions()[0][1]] -= 1;

                    } else if (X.getDomain()[2] == 1) {

                        tempNegColNumbers[X.getPositions()[0][1]] -= 1;
                        tempPosColNumbers[X.getPositions()[1][1]] -= 1;
                    }

                    for (int i = 0; i < rowNumber; i++) {
                        Variable tempVar = getVariable(variables, i, columnNumber);
                        if (tempVar.getDomainSize() == 2 || tempVar.getDomainSize() == 3) {
                            if (tempVar.getDomain()[2] == 1 && tempVar.getDomain()[1] == 1){
                                emptyHomes++;
                            }
                            else if (tempVar.getPositions()[0][0] == i && tempVar.getPositions()[0][1] == columnNumber) {
                                if (tempVar.getDomain()[1] == 1) tempPosColNumbers[columnNumber] -= 1;
                                else if (tempVar.getDomain()[2] == 1) tempNegColNumbers[columnNumber] -= 1;
                            } else {
                                if (tempVar.getDomain()[2] == 1) tempPosColNumbers[columnNumber] -= 1;
                                else if (tempVar.getDomain()[1] == 1) tempNegColNumbers[columnNumber] -= 1;
                            }
                        } else if(tempVar.getDomainSize() == 1){
                            if (tempVar.getPositions()[0][0] == i && tempVar.getPositions()[0][1] == columnNumber) {
                                if (tempVar.domain[1] == 1) {
                                    tempPosColNumbers[columnNumber] -= 1;
                                } else if (tempVar.domain[2] == 1) {
                                    tempNegColNumbers[columnNumber] -= 1;
                                }
                            } else {
                                if (tempVar.domain[1] == 1) {
                                    tempNegColNumbers[columnNumber] -= 1;
                                } else if (tempVar.domain[2] == 1) {
                                    tempPosColNumbers[columnNumber] -= 1;
                                }
                            }
                        }
                    }
                    if (!(emptyHomes >= tempNegColNumbers[columnNumber] + tempPosColNumbers[columnNumber])) {
                        //System.out.println("column number is :" + columnNumber + " emptyHomes is :" + emptyHomes + " tempnegcolnumbers is : " + tempNegColNumbers[columnNumber] + " tempPoscolNumbers is : " + tempPosColNumbers[columnNumber]);
                        removeDomain = true;
                        for (int i = 0; i < this.rowNumber; i++) {
                            tempNegRowNumbers[i] = negRowNumbers[i];
                            tempPosRowNumbers[i] = posRowNumbers[i];
                        }
                        for (int i = 0; i < this.columnNumber; i++) {
                            tempNegColNumbers[i] = negColNumbers[i];
                            tempPosColNumbers[i] = posColNumbers[i];
                        }
                        break;
                    }
                    for (int i = 0; i < this.rowNumber; i++) {
                        tempNegRowNumbers[i] = negRowNumbers[i];
                        tempPosRowNumbers[i] = posRowNumbers[i];
                    }
                    for (int i = 0; i < this.columnNumber; i++) {
                        tempNegColNumbers[i] = negColNumbers[i];
                        tempPosColNumbers[i] = posColNumbers[i];
                    }
                }
                remove[y][x] = removeDomain;
            }
        }
        System.arraycopy(XDomain, 0, X.domain, 0, 3);
        int[] newDomain = {YDomain[0] == 1 && remove[0][0] && remove[0][1] && remove[0][2] ? 0 : 1,YDomain[1] == 1 && remove[1][0] && remove[1][1] && remove[1][2] ? 0 : 1, YDomain[2] == 1 && remove[2][0] && remove[2][1] && remove[2][2] ? 0 : 1};
        Y.setDomain(newDomain);
        return ((remove[0][0] && remove[0][1] && remove[0][2]) || (remove[1][0] && remove[1][1] && remove[1][2]) || (remove[2][0] && remove[2][1] && remove[2][2]));
    }

    public void forwardChecking(ArrayList<Variable> variables, Variable selectedVar, int selectedDomain, int[][] neighborsDomain) {
        int numberOfNeighbors = 0;
        if (selectedVar.getPositions()[1][0] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0] - 1, selectedVar.getPositions()[1][1]);
            for (int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1]) {
                if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                else if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
            } else {
                isCompleteNeighbors = true;
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
            }

            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0] - 1, selectedVar.getPositions()[0][1]);
                for (int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                numberOfNeighbors++;
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] - 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                } else {
                    if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                    else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                }
            }
        }
        if (selectedVar.getPositions()[1][1] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] - 1);
            for (int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] - 1) {
                if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                else if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
            } else {
                isCompleteNeighbors = true;
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] - 1);
                for (int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                numberOfNeighbors++;
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] - 1) {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                } else {
                    if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                    else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                }
            }
        }
        if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0] + 1, selectedVar.getPositions()[0][1]);
            for (int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                isCompleteNeighbors = true;
            } else {
                if (selectedDomain == 2) {
                    tempVar.getDomain()[1] = 0;
                } else if (selectedDomain == 1) {
                    tempVar.getDomain()[2] = 0;
                }
            }
            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0] + 1, selectedVar.getPositions()[1][1]);
                for (int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                numberOfNeighbors++;
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1]) {
                    if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                    else if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                } else {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                }

            }
        }
        if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] + 1);
            for (int i = 0; i < 3; i++) {
                neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] + 1) {
                if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                isCompleteNeighbors = true;
            } else {
                if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] + 1);
                for (int i = 0; i < 3; i++) {
                    neighborsDomain[numberOfNeighbors][i] = tempVar.domain[i];
                }
                if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[1][1] + 1) {
                    if (selectedDomain == 2) tempVar.getDomain()[1] = 0;
                    else if (selectedDomain == 1) tempVar.getDomain()[2] = 0;
                } else {
                    if (selectedDomain != 0) tempVar.getDomain()[selectedDomain] = 0;
                }
            }
        }
    }

    public void load_PDomain_OfVarNeigh(ArrayList<Variable> variables, Variable selectedVar, int[][] neighboursDomain) {
        int numberOfNeighbors = 0;
        if (selectedVar.getPositions()[1][0] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0] - 1, selectedVar.getPositions()[1][1]);
            for (int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[1][0] == selectedVar.getPositions()[1][0] - 1 && tempVar.getPositions()[1][1] == selectedVar.getPositions()[1][1]) {
                isCompleteNeighbors = true;
            }
            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0] - 1, selectedVar.getPositions()[0][1]);
                for (int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
                numberOfNeighbors++;
            }
        }
        if (selectedVar.getPositions()[1][1] >= 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] - 1);
            for (int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[1][0] == selectedVar.getPositions()[1][0] && tempVar.getPositions()[1][1] == selectedVar.getPositions()[1][1] - 1) {
                isCompleteNeighbors = true;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] - 1);
                for (int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
                numberOfNeighbors++;
            }
        }
        if (selectedVar.getPositions()[0][0] < rowNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0] + 1, selectedVar.getPositions()[0][1]);
            for (int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] + 1 && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1]) {
                isCompleteNeighbors = true;
            }
            if (selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0] + 1, selectedVar.getPositions()[1][1]);
                for (int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
                numberOfNeighbors++;
            }
        }
        if (selectedVar.getPositions()[0][1] < columnNumber - 1) {
            boolean isCompleteNeighbors = false;
            Variable tempVar = getVariable(variables, selectedVar.getPositions()[0][0], selectedVar.getPositions()[0][1] + 1);
            for (int i = 0; i < 3; i++) {
                tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
            }
            numberOfNeighbors++;
            if (tempVar.getPositions()[0][0] == selectedVar.getPositions()[0][0] && tempVar.getPositions()[0][1] == selectedVar.getPositions()[0][1] + 1) {
                isCompleteNeighbors = true;
            }
            if (!selectedVar.isHorizontal() && !isCompleteNeighbors) {
                tempVar = getVariable(variables, selectedVar.getPositions()[1][0], selectedVar.getPositions()[1][1] + 1);
                for (int i = 0; i < 3; i++) {
                    tempVar.domain[i] = neighboursDomain[numberOfNeighbors][i];
                }
            }
        }

    }

    public Variable MRV(ArrayList<Variable> variables) {
        int min = 3;
        int index = 0;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getDomainSize() <= min) {
                index = i;
                min = variables.get(i).getDomainSize();
            }
        }
        return variables.get(index);

    }

    public ArrayList<Integer> LCV(ArrayList<Variable> variableArrayList, Variable variable) {

        Hashtable<String, Variable> variables = listToHash(variableArrayList);
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
                    key = (y1 - 1) + " " + x1;
                    Variable top_left_n = variables.get(key);
                    key = (y1 - 1) + " " + x2;
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
                    key = (y2 + 1) + " " + x1;
                    Variable bottom_left_n = variables.get(key);
                    key = (y2 + 1) + " " + x2;
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
                    key = y1 + " " + (x2 + 1);
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
                    key = (y1 - 1) + " " + x1;
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
                    key = (y2 + 1) + " " + x1;
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
        if (minValue != 0)
            ordering.add(minValue);
        if (minValue == 1 && variable.getDomain()[2] == 1)
            ordering.add(2);
        if (minValue == 2 && variable.getDomain()[1] == 1)
            ordering.add(1);

        return ordering;
    }

    //vList aval khalie, variables kole moteghayeras
    public ArrayList<Variable> CSP_BackTracking(ArrayList<Variable> vList, ArrayList<Variable> variables) {

        if (isComplete(vList))
            return vList;

        ArrayList<Variable> vPrimList = findOtherVariables(vList, variables);
        if (vPrimList.size() == 0) {
            return null;
        }

        AC3(variables, vPrimList);
        if(hasEmptyDomain(variables)){
            return null;
        }

        Variable var = MRV(vPrimList);
        ArrayList<Integer> ordering = LCV(variables, var);

        for (int v : ordering) {
            int[][] pos = var.getPositions();
            int[] preDomain = new int[3];
            System.arraycopy(var.domain, 0, preDomain, 0, 3);
            var.selectValue(v);
            vList.add(var);
            int[][] neighboursDomain = new int[6][3];
            forwardChecking(variables, var, v, neighboursDomain);
            if (hasEmptyDomain(variables)) {
                load_PDomain_OfVarNeigh(variables, var, neighboursDomain);
                vList.remove(var);
                return null;
            }
            ArrayList<Variable> result = CSP_BackTracking(vList, variables);
            if (result != null) {
                return result;
            }
            load_PDomain_OfVarNeigh(variables, var, neighboursDomain);
            System.arraycopy(preDomain, 0, var.domain, 0, 3);
            vList.remove(var);
        }
        return null;
    }

    private boolean hasEmptyDomain(ArrayList<Variable> vList) {

        for (int i = 0; i < vList.size(); i++) {
            if (vList.get(i).getDomainSize() == 0) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Variable> findOtherVariables(ArrayList<Variable> vList, ArrayList<Variable> allVariables) {
        ArrayList<Variable> vPrimList = new ArrayList<>();
        boolean found = false;
        for (Variable variable : allVariables) {
            for (Variable var : vList) {
                if (variable.equals(var)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                vPrimList.add(variable);
            }
            found = false;
        }
        return vPrimList;
    }

    private boolean isComplete(ArrayList<Variable> variableArrayList) {
        Variable var;
        int rowN = 0;
        int columnN = 0;
        int rowP = 0;
        int columnP = 0;
        String value;

        if (variableArrayList.size() != (rowNumber * columnNumber) / 2)
            return false; //age hame moteghayera meghdar naagerefte bashan

        Hashtable<String, Variable> variables = listToHash(variableArrayList);

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {

                var = variables.get(i + " " + j);
                value = var.selectedValue(i, j);
                if (value == "+")
                    rowP++;
                if (value == "-")
                    rowN++;
            }
            if (posRowNumbers[i] != rowP || negRowNumbers[i] != rowN)
                return false;
            rowN = 0;
            rowP = 0;
        }
        for (int j = 0; j < columnNumber; j++) {
            for (int i = 0; i < rowNumber; i++) {

                var = variables.get(i + " " + j);
                value = var.selectedValue(i, j);
                if (value == "+")
                    columnP++;
                if (value == "-")
                    columnN++;
            }
            if (posColNumbers[j] != columnP || negColNumbers[j] != columnN)
                return false;
            columnN = 0;
            columnP = 0;
        }
        return true;
    }

    public Hashtable<String, Variable> listToHash(ArrayList<Variable> variables) {
        Hashtable<String, Variable> varHash = new Hashtable<>();

        for (Variable var : variables) {
            int[][] positions = var.getPositions();
            varHash.put(positions[0][0] + " " + positions[0][1], var);
            varHash.put(positions[1][0] + " " + positions[1][1], var);
        }
        return varHash;
    }
}
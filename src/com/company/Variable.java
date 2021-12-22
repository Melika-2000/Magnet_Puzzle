package com.company;

import java.util.ArrayList;

public class Variable {
    private int[][] positions = new int[2][2];
    private boolean isMagnet;
    private char[] positionPoles = new char[2];
    int[] domain = {1, 1, 1};
    //// if domain[0] == 1 means that isMagnet can be false
    //// if domain[1] == 1 means that positionPoles[0] can be + and positionPoles[1] can be -
    //// if domain[2] == 1 means that positionPoles[0] can be - and positionPoles[1] can be +

    public Variable(int y1, int x1, int y2, int x2) {
        this.positions[0][0] = y1;
        this.positions[0][1] = x1;
        this.positions[1][0] = y2;
        this.positions[1][1] = x2;
        ////// always x1 > x2 || y1 > y2
    }

    public void setDomain(int[] domain) {
        this.domain = domain;
    }

    public int[][] getPositions() {
        return positions;
    }

    public boolean isMagnet() {
        return isMagnet;
    }

    public int[] getDomain() {
        return domain;
    }

    public char[] getPositionPoles() {
        return positionPoles;
    }

    public void setPositionPoles(char positionPole1, char positionPole2) {
        this.positionPoles[0] = positionPole1;
        this.positionPoles[1] = positionPole2;
    }

    public void setMagnet(boolean magnet) {
        isMagnet = magnet;
    }

    public ArrayList<Integer> getOtherPosition(ArrayList<Integer> position) {
        ArrayList<Integer> returnedArray = new ArrayList<>();
        if(position.get(0) == this.positions[0][0] && position.get(1) == this.positions[0][1]){
            returnedArray.add(this.positions[1][0]);
            returnedArray.add(this.positions[1][1]);
        }else if(position.get(0) == this.positions[0][0] && position.get(1) == this.positions[1][1]){
            returnedArray.add(this.positions[0][0]);
            returnedArray.add(this.positions[0][1]);
        }
        return returnedArray;
    }
}

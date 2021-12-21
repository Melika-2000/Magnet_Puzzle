package com.company;

public class Variable {
    int[][] positions = new int[2][2];
    private boolean isMagnet;
    private boolean isPositivePole;

    public Variable(int x1, int y1, int x2, int y2) {
        this.positions[0][1] = x1;
        this.positions[0][2] = y1;
        this.positions[1][1] = x2;
        this.positions[1][2] = y2;
    }

    public boolean isPositivePole() {
        return isPositivePole;
    }

    public void setPositivePole(boolean positivePole) {
        isPositivePole = positivePole;
    }

    public void setMagnet(boolean magnet) {
        isMagnet = magnet;
    }

}

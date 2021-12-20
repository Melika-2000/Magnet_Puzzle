package com.company;
public class Piece {
    private int x;
    private int y;
    private int x_OtherPole;
    private int y_OtherPole;
    private boolean isMagnet;
    private boolean isPositivePole;

    public Piece(int x, int y, int x_OtherPole, int y_OtherPole) {
        this.x = x;
        this.y = y;
        this.x_OtherPole = x_OtherPole;
        this.y_OtherPole = y_OtherPole;
    }

    public boolean isMagnet() {
        return isMagnet;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX_OtherPole() {
        return x_OtherPole;
    }

    public int getY_OtherPole() {
        return y_OtherPole;
    }

    public boolean isPositivePole() {
        return isPositivePole;
    }

    public void setMagnet(boolean magnet) {
        isMagnet = magnet;
    }

    public void setPositivePole(boolean positivePole) {
        isPositivePole = positivePole;
    }
}

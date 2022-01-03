package com.company;

public class Variable {
    private int[][] positions = new int[2][2];
    private boolean isMagnet = false;
    private char[] positionPoles = new char[2];
    int[] domain = {1, 1, 1};
    int[] Pdomain = {1, 1, 1};
    int[] Pdomain2 = {1, 1, 1};
    //int[] initialDomain
    //// if domain[0] == 1 means that isMagnet can be false
    //// if domain[1] == 1 means that positionPoles[0] can be + and positionPoles[1] can be -
    //// if domain[2] == 1 means that positionPoles[0] can be - and positionPoles[1] can be +

    public Variable(int y1, int x1, int y2, int x2) {
        this.positions[0][0] = y1; //i || i+1
        this.positions[0][1] = x1; //j || j+1
        this.positions[1][0] = y2; //i
        this.positions[1][1] = x2; //j
        ////// always x1 >= x2 || y1 >= y2
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

    public boolean isHorizontal() {
        if (positions[0][0] == positions[1][0])
            return true;

        return false;
    }

    public int[] getDomain() {
        return domain;
    }

    public int getDomainSize() {
        int size = 0;
        for (int i = 0; i < 3; i++) {
            if (domain[i] == 1)
                size++;
        }
        return size;
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

    public boolean getIsMagnet() {
        return isMagnet;
    }

    public String getOtherPosition(String position) {
        String returnedStr = "";
        try {
            int spaceIndex = position.indexOf(" ");
            String tempString = position.substring(0, spaceIndex);
            int rowNum = Integer.parseInt(tempString);
            tempString = position.substring(spaceIndex + 1, position.length());
            int colNum = Integer.parseInt(tempString);
            if (rowNum == this.positions[0][0] && colNum == this.positions[0][1]) {
                returnedStr += this.positions[1][0] + " " + this.positions[1][1];
            } else if (rowNum == this.positions[1][0] && colNum == this.positions[1][1]) {
                returnedStr += this.positions[0][0] + " " + this.positions[0][1];
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return returnedStr;
    }

    public void setPdomainToDomain() { ///////////////////////////////////////////////
        for (int i = 0; i < 3; i++) {
            domain[i] = Pdomain[i];
        }
    }

    public void selectValue(int d) {

        for (int i = 0; i < 3; i++) {
            Pdomain[i] = domain[i];
        }

        if (d != 0)
            this.setMagnet(true);

        if (domain[d] != 1) {
            System.out.println(this.getPositions()[0][0] + " " + this.getPositions()[0][1]);
            System.out.println("error");
            System.exit(7);
        }

        for (int i = 0; i < 3; i++) {
            if (i != d) {
                domain[i] = 0;
            } else
                domain[i] = 1;
        }

    }

    public void savePdomain() {
        for (int i = 0; i < 3; i++) {
            this.Pdomain2[i] = domain[i];
        }

    }

    public void loadPdomain() {

        for (int i = 0; i < 3; i++) {
            this.domain[i] = Pdomain2[i];
        }

    }

    public String selectedValue(int y, int x) {

        int selected = -1;
        if (this.getDomainSize() != 1)
            return "n";
        for (int i = 0; i < 3; i++) {
            if (domain[i] == 1)
                selected = i;
        }

        if (selected == -1)
            return null;
        if (selected == 0)
            return "0";

        if (selected == 1) {
            if ((this.isHorizontal() && x == positions[0][1]) ||
                    (!this.isHorizontal() && y == positions[0][0])) {
                return "+";
            } else
                return "-";
        }
        if (selected == 2) {
            if ((this.isHorizontal() && x == positions[0][1]) ||
                    (!this.isHorizontal() && y == positions[0][0])) {
                return "-";
            } else
                return "+";

        }
        return null;
    }

    public int getOtherPositionY(String position) {
        String yxString;
        int y;
        yxString = getOtherPosition(position);
        y = Integer.parseInt(yxString.substring(0, yxString.indexOf(" ")));
        return y;
    }

    public int getOtherPositionX(String position) {
        String yxString;
        int x;
        yxString = getOtherPosition(position);
        x = Integer.parseInt(yxString.substring(yxString.indexOf(" ") + 1));
        return x;
    }


}

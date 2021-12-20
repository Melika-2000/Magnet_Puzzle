package com.company;
public class Table {
    int RowNums;
    int ColNums;

    private Piece[][] tableMap = new Piece[RowNums][ColNums];

    public Table(int rowNums, int colNums, int[][] intTable) {
        this.RowNums = rowNums;
        this.ColNums = colNums;

        for(int i = 0; i < rowNums; i++){
            for(int j = 0; j < colNums; j++){
                this.tableMap[i][j].setMagnet(false);
            }
        }

        for(int i = 0; i < rowNums; i++){
            for(int j = 0; j < colNums; j++){
                if(i > 0 && intTable[i - 1][j] == intTable[i][j]){
                    this.tableMap[i][j] = new Piece(i,j,i-1,j);
                }
                if(i < rowNums - 1 && intTable[i + 1][j] == intTable[i][j]){
                    this.tableMap[i][j] = new Piece(i,j,i+1,j);
                }
                if(j > 0 && intTable[i][j - 1] == intTable[i][j]){
                    this.tableMap[i][j] = new Piece(i,j,i,j-1);
                }
                if(j < colNums - 1 && intTable[i][j + 1] == intTable[i][j]){
                    this.tableMap[i][j] = new Piece(i,j,i,j+1);
                }
            }
        }
    }

    public int getRowNums() {
        return RowNums;
    }

    public int getColNums() {
        return ColNums;
    }

    public Piece[][] getTableMap() {
        return tableMap;
    }

    public void setTableMap(Piece[][] tableMap) {
        this.tableMap = tableMap;
    }
}

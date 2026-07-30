package com.example.swisstournament2.Model;
public class RoundSwiss {
    private Long id;

    private int idPlayerW, idPlayerB, status, tableNum;

    public int getIdPlayerW() {
        return idPlayerW;
    }

    public void setIdPlayerW(int idPlayerW) {
        this.idPlayerW = idPlayerW;
    }

    public int getIdPlayerB() {
        return idPlayerB;
    }

    public void setIdPlayerB(int idPlayerB) {
        this.idPlayerB = idPlayerB;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public Long getId() {
        return id;
    }
}

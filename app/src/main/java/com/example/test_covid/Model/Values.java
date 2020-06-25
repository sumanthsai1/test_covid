package com.example.test_covid.Model;

public class Values {

    String con;
    String conn;
    String rec;
    String recc;
    String dec;
    String decc;

    public Values(String con, String conn, String rec, String recc, String dec, String decc) {
        this.con = con;
        this.conn = conn;
        this.rec = rec;
        this.recc = recc;
        this.dec = dec;
        this.decc = decc;
    }
    public Values(){

    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String getRecc() {
        return recc;
    }

    public void setRecc(String recc) {
        this.recc = recc;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getDecc() {
        return decc;
    }

    public void setDecc(String decc) {
        this.decc = decc;
    }
}

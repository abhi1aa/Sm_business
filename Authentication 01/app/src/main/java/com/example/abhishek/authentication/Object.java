package com.example.abhishek.authentication;

/**
 * Created by Abhishek on 03-10-2017.
 */

public class Object {
    String ocategory;
    String oname;
    Integer oval;
    String omsg;

    private Object() {
        ocategory = "";
        oname = "";
        oval = 0;
        omsg = "";
    }
    public Object(String ocategory, String oname, Integer oval, String omsg) {
        this.ocategory = ocategory;
        this.oname = oname;
        this.oval = oval;
        this.omsg = omsg;
    }

    public String getOcategory() {
        return ocategory;
    }

    public void setOcategory(String ocategory) {
        this.ocategory = ocategory;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public Integer getOval() {
        return oval;
    }

    public void setOval(Integer oval) {
        this.oval = oval;
    }

    public String getOmsg() {
        return omsg;
    }

    public void setOmsg(String omsg) {
        this.omsg = omsg;
    }
}


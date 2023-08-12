/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;


/**
 *
 * @author Admin
 */
public class Request implements Serializable{
    private int code;
    private Object o;
    public Request(int code, Object o) {
        this.code = code;
        this.o = o;
    }

    public int getCode() {
        return code;
    }

    public Object getO() {
        return o;
    }

}

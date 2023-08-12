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
public class Response implements Serializable{
    private int code;
    
    private Object res;

    public Response(int code, Object res) {
        this.code = code;
        this.res = res;
    }
    
    public int getCode(){
        return code;
    }
    
    public Object getRes(){
        return res;
    }
    
}

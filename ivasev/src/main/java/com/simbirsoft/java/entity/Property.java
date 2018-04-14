/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.entity;

/**
 *
 * @author admin
 */
public class Property {
    private String code;
    
    private String title;
    
    private String value;
    
    public Property(String code, String t, String v) {
        code = code;
        title = t;
        value = v;
    }
    
    public void setCode(String str){
        code = str;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setTitle(String str){
        title = str;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setValue(String str){
        value = str;
    }
    
    public String getValue() {
        return value;
    }
    
}

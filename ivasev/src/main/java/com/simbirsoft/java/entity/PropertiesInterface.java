/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.entity;

import java.util.Map;

/**
 *
 * @author admin
 */
public interface PropertiesInterface {

    Map<String, Property> getList();
    
    Property get(String key);
    
    void put(String key, Property val);
}

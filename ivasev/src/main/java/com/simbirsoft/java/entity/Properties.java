/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.simbirsoft.java.entity.Property;

/**
 *
 * @author admin
 */
public class Properties implements PropertiesInterface {
    
    private Map<String, Property> properties;
    private Path path = Paths.get("src/main/java/com/simbirsoft/java/properties");
    
    public Properties() throws IOException {
        properties = new HashMap<String, Property>() {
            {
                put("FIO", new Property("FIO", "ФИО", ""));
                put("DOB", new Property("DOB", "Дата рождения", ""));
                put("phone", new Property("phone", "Телефон", ""));
                put("email", new Property("email", "e-mail", ""));
                put("skype", new Property("skype", "Skype", ""));
                put("avatar", new Property("avatar", "", ""));
                put("target", new Property("target", "Цель", ""));
                put("experience", new Property("experience", "Опыт работы", ""));
                put("education", new Property("education", "Образование", ""));
                put("addeducation", new Property("addeducation", "Доп. образ. и курсы", ""));
            }
        };
        
        if (Files.exists(path)) {
            Files.lines(path).forEach((strln) -> {
                int seporat = strln.indexOf("=");
                String tag = strln.substring(0, seporat);
                Property prop = properties.get(tag);
                if (prop != null) {
                    prop.setValue(strln.substring(seporat + 1));
                }
            });
        } else {
            System.out.println("Файл не существует.");
        }
    }
    
    public Map<String, Property> getList() {
        return properties;
    }
    
    public Property get(String key){
        return properties.get(key);
    }
    
    public void put(String key, Property val){
        properties.put(key, val);
    }
}

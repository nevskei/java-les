/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.simbirsoft.java.entity.Property;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class ResumePropertiesImpl implements ResumeProperties {
    
    private Map<String, Property> properties;
    private Path path = Paths.get("src/main/java/com/simbirsoft/java/properties");
    
    public ResumePropertiesImpl() throws IOException {
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
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();           
        InputStream stream = loader.getResourceAsStream("myProp.properties");
        prop.load(new InputStreamReader(stream, Charset.forName("UTF-8")));
        properties.forEach((tag, property)->{
            String value = prop.getProperty(tag);
            if (value != null)
                property.setValue(value);
        });
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

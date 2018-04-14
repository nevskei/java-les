/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.Charset;

/**
 *
 * @author admin
 */
public class Properties implements PropertiesInterface {
    
    private Map<String, Property> properties;
    private final String DIR_NAME = "src/main/java/com/simbirsoft/java/";
    private final String EXTENSION = "properties";
    
    public Properties() throws IOException {
        System.out.println(2);
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
        File dir = new File(DIR_NAME);
        String[] filenames = dir.list(new ExtensionFilter(EXTENSION));
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < filenames.length; i++) {
            Thread thread = new ReadFilesThread(this, DIR_NAME+filenames[i]);
            threads.add(thread);
            thread.start();
        }
        threads.forEach((Thread thread)->{
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    private static class ExtensionFilter implements FilenameFilter {
 
        private final String extension;
 
        public ExtensionFilter(String ext) {
            extension = ext;
        }
 
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(extension);
        }
 
    }
    
    public class ReadFilesThread extends Thread {
        private Properties properties;
        private final String path;

        ReadFilesThread(Properties properties, String path) {
            this.properties = properties;
            this.path = path;
        }

        public void run() {
            java.util.Properties property = new java.util.Properties();
            try {
                property.load(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));
                properties.getList().forEach((key, prop)->{
                    String value = property.getProperty(key);
                    if (value != null) {
                        prop.setValue(value);
                    }
                }) ;
            } catch (FileNotFoundException e) {
                System.out.println("Не найден файл настроек");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
                
    }
}

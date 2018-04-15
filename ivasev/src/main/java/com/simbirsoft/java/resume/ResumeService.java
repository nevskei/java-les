/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.resume;

import java.lang.reflect.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class ResumeService {
    
    private Boolean fromDb = true;
    private final String DIR_NAME = "src/main/java/com/simbirsoft/java/";
    private final String EXTENSION = "properties";
    private ResumeRepository repository;

    public ResumeService(ResumeRepository repository) {
        this.repository = repository;
    }
    
    
    
    public ResumeDto getResume() {

            Resume resume = repository.getOne(1L);
        if (resume == null) {
            ResumeDto resumeDto = new ResumeDto();
            File dir = new File(DIR_NAME);
            String[] filenames = dir.list(new ResumeService.ExtensionFilter(EXTENSION));
            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < filenames.length; i++) {
                Thread thread = new ResumeService.ReadFilesThread(resumeDto, DIR_NAME+filenames[i]);
                threads.add(thread);
                thread.start();
            }
            threads.forEach((Thread thread)->{
                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ResumeService.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return resumeDto;
        }
        return resume;
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
        private ResumeDto resume;
        private final String path;

        ReadFilesThread(ResumeDto resume, String path) {
            this.resume = resume;
            this.path = path;
        }

        public void run() {
            java.util.Properties property = new java.util.Properties();
            try {
                property.load(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));
                Method[] methods = resume.getClass().getMethods();
                
                property.forEach((key, prop)->{
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("set");
                    char[] c = key.toString().toLowerCase().toCharArray();
                    c[0] = Character.toUpperCase(c[0]);
                    stringBuilder.append(c);
                    for (Method m : methods) {
                      if (m.getName().equals(stringBuilder.toString())) {
                          try {
                              m.invoke(resume, prop.toString());
                          } catch (IllegalAccessException ex) {
                              Logger.getLogger(ResumeService.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (IllegalArgumentException ex) {
                              Logger.getLogger(ResumeService.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (InvocationTargetException ex) {
                              Logger.getLogger(ResumeService.class.getName()).log(Level.SEVERE, null, ex);
                          }
                        break;
                      }
                    }
                });
                
                /*
                properties.getList().forEach((key, prop)->{
                    String value = property.getProperty(key);
                    if (value != null) {
                        prop.setValue(value);
                    }
                }) ;
                /**/
            } catch (FileNotFoundException e) {
                System.out.println("Не найден файл настроек");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }           
    }
}
